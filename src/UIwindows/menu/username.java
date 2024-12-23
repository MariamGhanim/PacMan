package UIwindows.menu;

import UIwindows.PlayGame;
import logic.SoundManager;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class username {
    public static String userName;
    private static JPanel backButtonPanel;

    public static void showUsername(JFrame gameWindow) {
        gameWindow.getContentPane().setBackground(Color.YELLOW);
        GLCanvas canvas = new GLCanvas();
        gameWindow.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.YELLOW);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel nameLabel = new JLabel("Your Username");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 40));
        nameLabel.setForeground(Color.BLACK);


        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        nameField.setBorder(new LineBorder(Color.BLACK, 2, true));


        JButton submitButton = new JButton("Next");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setBackground(Color.GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);


        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            choosePlayers.showchoosePlayers(gameWindow);


        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = nameField.getText();
                if (!userName.isEmpty()) {
                    JOptionPane.showMessageDialog(gameWindow, "Name saved: " + userName);
                    inputPanel.setVisible(false);
                    gameWindow.getContentPane().remove(backButtonPanel);
                    ChooseLevels.showLevels(gameWindow);
                } else {
                    JOptionPane.showMessageDialog(gameWindow, "Please enter a valid name.");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridy = 2;
        inputPanel.add(submitButton, gbc);

        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(Color.YELLOW);
        backButtonPanel.add(backButton, BorderLayout.WEST);
        gameWindow.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);
        gameWindow.getContentPane().add(inputPanel, BorderLayout.CENTER);
        gameWindow.getContentPane().add(canvas, BorderLayout.NORTH);


        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
