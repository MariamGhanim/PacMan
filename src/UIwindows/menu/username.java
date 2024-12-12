package UIwindows.menu;

import UIwindows.PlayGame;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class username {
    // Static variable to store the user's name
    public static String userName;

    // Declare backButtonPanel as a field to access it outside the method
    private static JPanel backButtonPanel;

    public static void showUsername(JFrame gameWindow) {
        // Set the background color of the window to yellow
        gameWindow.getContentPane().setBackground(Color.YELLOW);

        // Create the OpenGL canvas
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        // Set layout for the JFrame
        gameWindow.setLayout(new BorderLayout());

        // Panel for the username input with GridBagLayout to center everything
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.YELLOW); // Yellow background for the input panel

        // Constraints to center the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.anchor = GridBagConstraints.CENTER;

        // Large, bold label for "Your Username"
        JLabel nameLabel = new JLabel("Your Username");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Big black font
        nameLabel.setForeground(Color.BLACK);

        // Text field for the username input with larger and curved style
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20)); // Larger font for better readability
        nameField.setBackground(Color.WHITE); // White background for the text field
        nameField.setForeground(Color.BLACK); // Black text color
        nameField.setBorder(new LineBorder(Color.BLACK, 2, true)); // Rounded corners with black border

        // Button to submit the username
        JButton submitButton = new JButton("Next");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Bigger button text
        submitButton.setBackground(Color.GRAY); // Grey button
        submitButton.setForeground(Color.WHITE); // White button text
        submitButton.setFocusPainted(false); // Remove focus ring from the button

        // Button to go back to the start screen
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);  // Hide current window
                JFrame startWindow = new JFrame("Start or Exit");
                new choosePlayers().showchoosePlayers(startWindow);
                startWindow.setSize(800, 600);  // Set size for the new window
                startWindow.setResizable(true); // Allow resizing the new window
                startWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window properly
                startWindow.setVisible(true);  // Make sure the new window is visible
            }
        });

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = nameField.getText(); // Save the name entered by the user
                if (!userName.isEmpty()) {
                    JOptionPane.showMessageDialog(gameWindow, "Name saved: " + userName);

                    // Hide the current username input panel
                    inputPanel.setVisible(false);

                    // Remove the back button panel before transitioning to the levels screen
                    gameWindow.getContentPane().remove(backButtonPanel);

                    // Show the levels screen after the username is saved
                    Levels.showLevels(gameWindow); // Call the levels class to show the next screen
                } else {
                    JOptionPane.showMessageDialog(gameWindow, "Please enter a valid name.");
                }
            }
        });

        // Add components to the input panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridy = 2;
        inputPanel.add(submitButton, gbc);

        // Create a new panel for the back button with BorderLayout
        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout()); // Set the layout to BorderLayout
        backButtonPanel.setBackground(Color.YELLOW); // Set the background to match the game window

        // Add the back button to the left side of the bottom region
        backButtonPanel.add(backButton, BorderLayout.WEST); // Use BorderLayout.WEST for left alignment

        // Add the backButtonPanel to the South of the JFrame
        gameWindow.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        // Add the input panel to the JFrame
        gameWindow.getContentPane().add(inputPanel, BorderLayout.CENTER);

        // Add the OpenGL canvas to the JFrame (adjust positioning)
        gameWindow.getContentPane().add(canvas, BorderLayout.NORTH); // Move canvas to the top

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
