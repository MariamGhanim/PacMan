package UIwindows.menu;

import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choosePlayers {

    public static void showchoosePlayers(JFrame gameWindow) {
        GLCanvas canvas = new GLCanvas();
        gameWindow.getContentPane().setBackground(new Color(190, 200, 220));

        // Main panel with GridBagLayout for vertical alignment
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton multiPlayerButton = new JButton("Multiplayer");
        JButton backButton = new JButton("Back");

        styleButton(singlePlayerButton);
        styleButton(multiPlayerButton);
        styleButton(backButton);

        // GridBagConstraints for vertical placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Space between buttons
        gbc.gridx = 0; // Center horizontally
        gbc.fill = GridBagConstraints.NONE; // No resizing for buttons

        // Add buttons to the panel
        gbc.gridy = 0; // First button
        buttonPanel.add(singlePlayerButton, gbc);
        gbc.gridy = 1; // Second button
        buttonPanel.add(multiPlayerButton, gbc);
        gbc.gridy = 2; // Third button
        buttonPanel.add(backButton, gbc);

        // Add action listeners
        singlePlayerButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            username.showUsername(gameWindow);
        });

        multiPlayerButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            twoUsername.showTwoUsernames(gameWindow);
        });

        backButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            startOrExit.showMenu(gameWindow);
        });

        // Add panel to the window
        gameWindow.getContentPane().setLayout(new BorderLayout());
        gameWindow.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        gameWindow.revalidate();
        gameWindow.repaint();

        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void styleButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50)); // Fixed size for consistent design
    }
}