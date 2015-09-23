/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author samharju
 */
public class GUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout mainPanelLayout;
    private JPanel menuPanel;
    private JPanel gamePanel;
    
     public GUI() {
        initializeFrame();
        initializeMainPanel();
        setupMainMenu();
        setupBoardView();
        setupAndStartGUI();
}
     
     private void setupBoardView() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        JPanel boardPanel = new JPanel();
        GridLayout boardLayout = new GridLayout(9, 9);
        boardPanel.setLayout(boardLayout);

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 20));
                button.setBackground(Color.white);
                boardPanel.add(button);
                buttons.add(button);
            }
        }

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new CompoundBorder(bottomPanel.getBorder(), new EmptyBorder(10,10,10,10)));

        JLabel mistakeCounter = new JLabel("Number of mistakes: 0");

        JButton resetButton = new JButton("Reset");

        JButton exitButton = new JButton("Exit");

        bottomPanel.add(mistakeCounter);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(resetButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(exitButton);

        gamePanel.add(boardPanel);
        gamePanel.add(bottomPanel);
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

        JComboBox levelCombo = new JComboBox();
        levelCombo.setMaximumSize(new Dimension(200, 20));
        levelCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        frame.setSize(600,600);
        frame.setAutoRequestFocus(true);
    }
}
