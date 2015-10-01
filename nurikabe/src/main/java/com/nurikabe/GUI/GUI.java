/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author samharju
 */
public class GUI {

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
    JComboBox levelCombo;

    public GUI(Nurikabe nurikabe) {
        this.logic = nurikabe;
        initializeFrame();
        initializeMainPanel();
        setupMainMenu();
        setupBoardView();
        setupAndStartGUI();
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
                for (JButton button: boardButtons) {
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

    private void exitBoard() {
        mainPanelLayout.first(mainPanel);
        boardButtons.clear();
        boardPanel.removeAll();
        createBoardButtons();
    }

    private void createBoardButtons() {
        boardButtons = new ArrayList<JButton>();
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

    private void setupAndStartGUI() {
        mainPanel.add(menuPanel);
        mainPanel.add(gamePanel);
        frame.add(mainPanel);
        frame.setVisible(true);
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
                logic.buildLevel((int) levelCombo.getSelectedItem());
                mainPanelLayout.last(mainPanel);
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

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanelLayout = new CardLayout();
        mainPanel.setLayout(mainPanelLayout);
    }

    private void initializeFrame() throws HeadlessException {
        frame = new JFrame("Nurikabe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setAutoRequestFocus(true);
    }

    public void updateMistakes(int numberOfMistakes) {
        mistakeCounter.setText("Number of mistakes: " + numberOfMistakes);
    }
    
    public void setBoardButtonLabel(int buttonNumber, int label) {
        JButton buttonToSet = boardButtons.get(buttonNumber);
        buttonToSet.setText("" + label);
    }
}
