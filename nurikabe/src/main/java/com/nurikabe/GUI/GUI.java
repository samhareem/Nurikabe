/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.GUI;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author samharju
 */
public class GUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout mainPanelLayout;
    private JPanel menuPanel;
    
     public GUI() {
        initializeFrame();
        initializeMainPanel();
        setupMainMenu();
        setupAndStartGUI();
}
     
     private void setupAndStartGUI() {
         mainPanel.add(menuPanel);
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
