package UIwindows.menu;

import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class twoUsername {
    // Static variables to store the usernames
    public static String userName1;
    public static String userName2;

    // Declare backButtonPanel as a field to access it later
    private static JPanel backButtonPanel;

    public static void showTwoUsernames(JFrame gameWindow) {
        // Set the background color of the window to yellow
        gameWindow.getContentPane().setBackground(Color.YELLOW);

        // Create the OpenGL canvas
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        // Set layout for the JFrame (using BorderLayout)
        gameWindow.setLayout(new BorderLayout());

        // Panel for the username input with GridBagLayout to center everything
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.YELLOW); // Yellow background for the input panel

        // Constraints to center the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.anchor = GridBagConstraints.CENTER;

        // Large, bold label for "Player 1 Username"
        JLabel nameLabel1 = new JLabel("Player 1 Username");
        nameLabel1.setFont(new Font("Arial", Font.BOLD, 40)); // Big black font
        nameLabel1.setForeground(Color.BLACK);

        // Text field for the first username input
        JTextField nameField1 = new JTextField(20);
        nameField1.setFont(new Font("Arial", Font.PLAIN, 20)); // Larger font for better readability
        nameField1.setBackground(Color.WHITE); // White background for the text field
        nameField1.setForeground(Color.BLACK); // Black text color
        nameField1.setBorder(new LineBorder(Color.BLACK, 2, true)); // Rounded corners with black border

        // Large, bold label for "Player 2 Username"
        JLabel nameLabel2 = new JLabel("Player 2 Username");
        nameLabel2.setFont(new Font("Arial", Font.BOLD, 40)); // Big black font
        nameLabel2.setForeground(Color.BLACK);

        // Text field for the second username input
        JTextField nameField2 = new JTextField(20);
        nameField2.setFont(new Font("Arial", Font.PLAIN, 20)); // Larger font for better readability
        nameField2.setBackground(Color.WHITE); // White background for the text field
        nameField2.setForeground(Color.BLACK); // Black text color
        nameField2.setBorder(new LineBorder(Color.BLACK, 2, true)); // Rounded corners with black border

        // Button to submit the usernames
        JButton submitButton = new JButton("Next");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Bigger button text
        submitButton.setBackground(Color.GRAY); // Grey button
        submitButton.setForeground(Color.WHITE); // White button text
        submitButton.setFocusPainted(false); // Remove focus ring from the button

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName1 = nameField1.getText(); // Save the first name entered by the user
                userName2 = nameField2.getText(); // Save the second name entered by the user
                if (!userName1.isEmpty() && !userName2.isEmpty()) {
                    JOptionPane.showMessageDialog(gameWindow, "Player 1: " + userName1 + "\nPlayer 2: " + userName2);

                    // Hide the current username input panel
                    inputPanel.setVisible(false);

                    // Remove the back button panel before transitioning to the levels screen
                    gameWindow.getContentPane().remove(backButtonPanel);

                    // Show the levels screen after the usernames are saved
                    twoLevels.showTwoLevels(gameWindow); // Call the levels class to show the next screen
                } else {
                    JOptionPane.showMessageDialog(gameWindow, "Please enter valid names for both players.");
                }
            }
        });

        // Add components to the input panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel1, gbc);

        gbc.gridy = 1;
        inputPanel.add(nameField1, gbc);

        gbc.gridy = 2;
        inputPanel.add(nameLabel2, gbc);

        gbc.gridy = 3;
        inputPanel.add(nameField2, gbc);

        gbc.gridy = 4;
        inputPanel.add(submitButton, gbc);

        // Create a new panel for the back button with BorderLayout
        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout()); // Set the layout to BorderLayout
        backButtonPanel.setBackground(Color.YELLOW); // Set the background to match the game window

        // Create the back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY); // Red background for the back button
        backButton.setForeground(Color.WHITE); // White text color

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous screen (Start screen or username screen)
                gameWindow.setVisible(false);  // Hide the current window
                JFrame startWindow = new JFrame("Start or Exit");
                new choosePlayers().showchoosePlayers(startWindow); // Call the method to show the previous screen
                startWindow.setSize(800, 600);  // Set size for the new window
                startWindow.setResizable(true); // Allow resizing the new window
                startWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                startWindow.setVisible(true);  // Make sure the new window is visible
            }
        });

        // Add the back button to the left side of the bottom region
        backButtonPanel.add(backButton, BorderLayout.WEST); // Use BorderLayout.WEST for left alignment

        // Add the backButtonPanel to the South of the JFrame (This should make it visible)
        gameWindow.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        // Add the input panel to the JFrame
        gameWindow.getContentPane().add(inputPanel, BorderLayout.CENTER);

        // Add the OpenGL canvas to the JFrame (if needed)
        gameWindow.getContentPane().add(canvas, BorderLayout.NORTH); // Changed to NORTH for better positioning

        // Revalidate the window to apply changes
        gameWindow.revalidate();
        gameWindow.repaint();

        // Start the OpenGL animator
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();

        // Set up the main window's properties
        gameWindow.setSize(800, 600); // Set initial size for the main window
        gameWindow.setResizable(true); // Allow resizing the window
        gameWindow.setVisible(true);  // Make sure the game window is visible
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window properly
    }
}
