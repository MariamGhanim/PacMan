package UIwindows.menu;

import UIwindows.menu.choosePlayers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class startOrExit {
    public static void showMenu(JFrame gameWindow) {

        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundIcon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\PacMan_New\\src\\Assets\\titleScreen.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setLayout(null);

        // Create Buttons
        JButton startButton = new JButton("Start Game");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        // Common Button Styling
        JButton[] buttons = {startButton, helpButton, exitButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.YELLOW);
            button.setFocusPainted(false);
            menuPanel.add(button);
        }

        // Add Action Listeners
        startButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            choosePlayers.showchoosePlayers(gameWindow);
        });

        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(gameWindow,
                "Instructions:\n1. Use arrow keys to move.\n2. Avoid ghosts.\n3. Collect all items to win.",
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE));

        exitButton.addActionListener(e -> System.exit(0));

        // Add a ComponentListener to handle resizing
        gameWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                // Get current window dimensions
                int windowWidth = gameWindow.getWidth();
                int windowHeight = gameWindow.getHeight();


                int buttonWidth = (int) (windowWidth * 0.2); // 20% of window width
                int buttonHeight = (int) (windowHeight * 0.08); // 8% of window height
                int buttonX = (windowWidth - buttonWidth) / 2; // Center horizontally

                int buttonSpacing = (int) (windowHeight * 0.05);
                int firstButtonY = (int) (windowHeight * 0.5);

                // Set bounds for buttons
                startButton.setBounds(buttonX, firstButtonY, buttonWidth, buttonHeight);
                helpButton.setBounds(buttonX, firstButtonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight); // Place Help below Start
                exitButton.setBounds(buttonX, firstButtonY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight); // Place Exit below Help
            }
        });


        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);
        gameWindow.revalidate();
        gameWindow.repaint();

        SwingUtilities.invokeLater(() -> gameWindow.dispatchEvent(new java.awt.event.ComponentEvent(
                gameWindow, java.awt.event.ComponentEvent.COMPONENT_RESIZED)));
    }
}
