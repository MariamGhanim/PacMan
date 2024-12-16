package UIwindows.menu;

import UIwindows.PlayGame;
import logic.SoundManager;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choosePlayers {

    public static void showchoosePlayers(JFrame gameWindow) {
        GLCanvas canvas = new GLCanvas();
        gameWindow.getContentPane().setBackground(new Color(190, 200, 220));



        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        JButton singlePlayerButton = new JButton("Single Player");
        JButton multiPlayerButton = new JButton("Multiplayer");
        JButton backButton = new JButton("Back");

        styleButton(singlePlayerButton);
        styleButton(multiPlayerButton);
        styleButton(backButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;


        gbc.gridy = 0;
        buttonPanel.add(singlePlayerButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(multiPlayerButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(backButton, gbc);


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
            SoundManager.stopSound();
        });


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
        button.setPreferredSize(new Dimension(150, 50));
    }
}