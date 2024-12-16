package UIwindows.menu;
import UIwindows.PlayGame;
import logic.SoundManager;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class twoUsername {
    public static String userName1;
    public static String userName2;
    private static JPanel backButtonPanel;
    public static void showTwoUsernames(JFrame gameWindow) {
        gameWindow.getContentPane().setBackground(Color.YELLOW);
        GLCanvas canvas = new GLCanvas();
        gameWindow.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.YELLOW);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel nameLabel1 = new JLabel("Player 1 Username");
        nameLabel1.setFont(new Font("Arial", Font.BOLD, 40));
        nameLabel1.setForeground(Color.BLACK);

        JTextField nameField1 = new JTextField(20);
        nameField1.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField1.setBackground(Color.WHITE);
        nameField1.setForeground(Color.BLACK);
        nameField1.setBorder(new LineBorder(Color.BLACK, 2, true));

        JLabel nameLabel2 = new JLabel("Player 2 Username");
        nameLabel2.setFont(new Font("Arial", Font.BOLD, 40));
        nameLabel2.setForeground(Color.BLACK);


        JTextField nameField2 = new JTextField(20);
        nameField2.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField2.setBackground(Color.WHITE);
        nameField2.setForeground(Color.BLACK);
        nameField2.setBorder(new LineBorder(Color.BLACK, 2, true));


        JButton submitButton = new JButton("Next");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setBackground(Color.GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName1 = nameField1.getText();
                userName2 = nameField2.getText();
                if (!userName1.isEmpty() && !userName2.isEmpty()) {
                    JOptionPane.showMessageDialog(gameWindow, "Player 1: " + userName1 + "\nPlayer 2: " + userName2);
                    inputPanel.setVisible(false);
                    gameWindow.getContentPane().remove(backButtonPanel);
                    ChooseMpLevels.showMpLevels(gameWindow);
                } else {
                    JOptionPane.showMessageDialog(gameWindow, "Please enter valid names for both players.");
                }
            }
        });


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


        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(Color.YELLOW);


        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        backButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            choosePlayers.showchoosePlayers(gameWindow);


        });

        backButtonPanel.add(backButton, BorderLayout.WEST);
        gameWindow.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);
        gameWindow.getContentPane().add(inputPanel, BorderLayout.CENTER);
        gameWindow.getContentPane().add(canvas, BorderLayout.NORTH);
        gameWindow.revalidate();
        gameWindow.repaint();

        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);//علشان تظهر في نص الشاشه

        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
