package com.nurikabe.GUI;

import com.nurikabe.controller.Nurikabe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * The class contains all the GUI information for the Nurikabe program.
 *
 * @author samharju
 */
public class Gui {

    private Nurikabe logic;
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout mainPanelLayout;
    private JPanel menuPanel;
    private JPanel gamePanel;
    private JPanel boardPanel;
    private ArrayList<JButton> boardButtons;
    private ActionListener buttonListener;
    private JLabel mistakeCounter;
    private JComboBox levelCombo;
    
    /**
     v* 
     * Constructor for Gui class. Sets up and starts GUI.
     * 
     * @param nurikabe  Nurikabe class to be used with the GUI.
     */
    public Gui(Nurikabe nurikabe) {
        this.logic = nurikabe;
        initializeFrame();
        initializeMainPanel();
        setupMainMenu();
        setupBoardView();
        setupAndStartGUI();
    }
    
    /**
     * Updates label specifying number of mistakes.
     *
     * @param numberOfMistakes Number to update the label to.
     */
    public void updateMistakes(int numberOfMistakes) {
        mistakeCounter.setText("Number of mistakes: " + numberOfMistakes);
    }

    /**
     * Sets label of board button to specified number.
     *
     * @param buttonNumber Specifies button to update.
     * @param label Specifies what number to update the label to.
     */
    public void setBoardButtonLabel(int buttonNumber, int label) {
        JButton buttonToSet = boardButtons.get(buttonNumber);
        buttonToSet.setText("" + label);
    }
    
    private void initializeFrame() throws HeadlessException {
        frame = new JFrame("Nurikabe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setAutoRequestFocus(true);
    }
    
    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanelLayout = new CardLayout();
        mainPanel.setLayout(mainPanelLayout);
    }
    
    private void setupMainMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Super Ultra Mega-good Nurikabe");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel levelComboLabel = new JLabel("Choose level:");
        levelComboLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        levelCombo = new JComboBox();
        levelCombo.setMaximumSize(new Dimension(200, 20));
        levelCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (int n = 1; n <= logic.getNumberOfLevels(); n++) {
            levelCombo.addItem(n);
        }

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent startClick) {
                if (!logic.buildLevel((int) levelCombo.getSelectedItem())) {
                    JOptionPane.showMessageDialog(frame, "Problem with level file.");
                    return;
                } else {
                    mainPanelLayout.last(mainPanel);
                }
            }
        };
        startButton.addActionListener(startListener);

        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ActionListener quitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent quitClick) {
                frame.dispose();
            }
        };
        quitButton.addActionListener(quitListener);

        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(titleLabel);
        menuPanel.add(Box.createVerticalStrut(50));
        menuPanel.add(levelComboLabel);
        menuPanel.add(Box.createVerticalStrut(5));
        menuPanel.add(levelCombo);
        menuPanel.add(Box.createVerticalStrut(25));
        menuPanel.add(startButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(quitButton);
        menuPanel.add(Box.createVerticalStrut(10));
    }

    private void setupBoardView() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        boardPanel = new JPanel();
        GridLayout boardLayout = new GridLayout(9, 9);
        boardPanel.setLayout(boardLayout);

        createBoardButtons();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new CompoundBorder(bottomPanel.getBorder(), new EmptyBorder(10, 10, 10, 10)));

        mistakeCounter = new JLabel("Number of mistakes: 0");

        JButton resetButton = new JButton("Reset");
        ActionListener resetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent resetClick) {
                for (JButton button : boardButtons) {
                    if (button.isEnabled() == false) {
                        button.setEnabled(true);
                        button.setBackground(Color.white);
                    }
                }
                logic.resetBoard();
            }
        };
        resetButton.addActionListener(resetListener);

        JButton exitButton = new JButton("Exit");
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitClick) {
                exitBoard();
            }
        };
        exitButton.addActionListener(exitListener);

        bottomPanel.add(mistakeCounter);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(resetButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(exitButton);

        gamePanel.add(boardPanel);
        gamePanel.add(bottomPanel);
    }
    
    private void createBoardButtons() {
        boardButtons = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                JButton button = new JButton();
                ActionListener boardListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent boardClick) {
                        int buttonNumber = getButtonNumber((JButton) boardClick.getSource());
                        if (logic.checkGrid(buttonNumber % 9, buttonNumber / 9)) {
                            JButton sourceButton = (JButton) boardClick.getSource();
                            sourceButton.setBackground(Color.black);
                            sourceButton.setEnabled(false);
                            if (logic.checkIfComplete()) {
                                JOptionPane.showMessageDialog(frame, "You win!");
                                exitBoard();
                            }
                        }
                    }
                };
                button.addActionListener(boardListener);
                button.setFont(new Font("Arial", Font.BOLD, 20));
                button.setBackground(Color.white);
                boardPanel.add(button);
                boardButtons.add(button);
            }
        }
    }
    
    private int getButtonNumber(JButton button) {
        for (int index = 0; index < boardButtons.size(); index++) {
            JButton temp = boardButtons.get(index);
            if (button == temp) {
                return index;
            }
        }
        return -1;
    }
    
    private void exitBoard() {
        mainPanelLayout.first(mainPanel);
        boardButtons.clear();
        boardPanel.removeAll();
        createBoardButtons();
    }

    private void setupAndStartGUI() {
        mainPanel.add(menuPanel);
        mainPanel.add(gamePanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
