package UIwindows.menu;

import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choosePlayers {

    public static void showchoosePlayers(JFrame gameWindow) {
        // Create the OpenGL canvas
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        // Set the background color to light yellow
        gameWindow.getContentPane().setBackground(new Color(190, 200, 220)); // Light yellow

        // Create a panel for buttons and set layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);  // Use null layout for manual positioning
        buttonPanel.setOpaque(false);  // Make the panel transparent

        // Create buttons
        JButton singlePlayerButton = new JButton("Single Player");
        JButton multiPlayerButton = new JButton("Multiplayer");
        JButton backButton = new JButton("Back");

        // Style buttons and set small sizes
        styleButton(singlePlayerButton);
        styleButton(multiPlayerButton);
        styleButton(backButton);

        // Set button positions manually (you can adjust these values as per your requirement)
        singlePlayerButton.setBounds(300, 200, 150, 50);  // (x, y, width, height)
        multiPlayerButton.setBounds(300, 270, 150, 50);
        backButton.setBounds(300, 340, 150, 50);

        // Add buttons to the panel
        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(multiPlayerButton);
        buttonPanel.add(backButton);

        // Add action listeners for the buttons
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);  // Hide current window
                JFrame usernameWindow = new JFrame("Username");
                new username().showUsername(usernameWindow);
                usernameWindow.setSize(800, 600);  // Set size for the new window
                usernameWindow.setResizable(true); // Allow resizing the new window
                usernameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                usernameWindow.setVisible(true);  // Make sure the new window is visible
            }
        });

        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);  // Hide current window
                JFrame twoUsernameWindow = new JFrame("Two Username");
                new twoUsername().showTwoUsernames(twoUsernameWindow);
                twoUsernameWindow.setSize(800, 600);  // Set size for the new window
                twoUsernameWindow.setResizable(true); // Allow resizing the new window
                twoUsernameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                twoUsernameWindow.setVisible(true);  // Make sure the new window is visible
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

        // Add the button panel to the frame
        gameWindow.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Revalidate the window to apply changes
        gameWindow.revalidate();
        gameWindow.repaint();

        // Start the OpenGL animator
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();

        // Set up the main window's properties
        gameWindow.setSize(800, 600); // Set initial size for the main window
        gameWindow.setResizable(true); // Allow resizing the window
        gameWindow.setVisible(true); // Make sure the game window is visible
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app properly when main window is closed
    }

    // Method to style buttons with grey background and white font
    private static void styleButton(JButton button) {
        button.setBackground(Color.GRAY);  // Grey background
        button.setForeground(Color.WHITE); // White text
        button.setFont(new Font("Arial", Font.BOLD, 14));  // Smaller font size
        button.setFocusPainted(false);  // Remove the border around the button when clicked
        button.setPreferredSize(new Dimension(120, 50));  // Button size
    }
}
