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
        canvas.addGLEventListener(new PlayGame.GameRenderer());
        gameWindow.getContentPane().setBackground(new Color(190, 200, 220));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton multiPlayerButton = new JButton("Multiplayer");
        JButton backButton = new JButton("Back");


        styleButton(singlePlayerButton);
        styleButton(multiPlayerButton);
        styleButton(backButton);

        singlePlayerButton.setBounds(300, 200, 150, 50);  // (x, y, width, height)
        multiPlayerButton.setBounds(300, 270, 150, 50);
        backButton.setBounds(300, 340, 150, 50);

        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(multiPlayerButton);
        buttonPanel.add(backButton);

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);  // Hide current window
                JFrame usernameWindow = new JFrame("Username");
                new username().showUsername(usernameWindow);
                usernameWindow.setSize(800, 600);  // Set size for the new window
                usernameWindow.setLocationRelativeTo(null);
                usernameWindow.setResizable(true); // Allow resizing the new window
                usernameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                usernameWindow.setVisible(true);  // Make sure the new window is visible
            }
        });

        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);
                JFrame twoUsernameWindow = new JFrame("Two Username");
                new twoUsername().showTwoUsernames(twoUsernameWindow);
                twoUsernameWindow.setSize(800, 600);
                twoUsernameWindow.setResizable(true);
                twoUsernameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                twoUsernameWindow.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);  // Hide current window
                JFrame startWindow = new JFrame("Start or Exit");
                new startOrExit().showMenu(startWindow);
                startWindow.setSize(800, 600);  // Set size for the new window
                startWindow.setResizable(true); // Allow resizing the new window
                startWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                startWindow.setVisible(true);  // Make sure the new window is visible
            }
        });

        gameWindow.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        gameWindow.revalidate();
        gameWindow.repaint();


        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
        gameWindow.setSize(800, 600); // ج initial size for the main window
        gameWindow.setResizable(true); // Allow resizing the window
        gameWindow.setVisible(true); // ج sure the game window is visible
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ج the app properly when main window is closed
    }


    private static void styleButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 50));
    }
}
