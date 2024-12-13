package UIwindows.menu;
import UIwindows.PlayGame;
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
        canvas.addGLEventListener(new PlayGame.GameRenderer());
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

        // Button to submit the usernames
        JButton submitButton = new JButton("Next");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setBackground(Color.GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName1 = nameField1.getText();
                userName2 = nameField2.getText();
                if (!userName1.isEmpty() && !userName2.isEmpty()) {
                    JOptionPane.showMessageDialog(gameWindow, "Player 1: " + userName1 + "\nPlayer 2: " + userName2);

                    // Hide the current username input panel
                    inputPanel.setVisible(false);

                    // Remove the back button panel before transitioning to the levels screen
                    gameWindow.getContentPane().remove(backButtonPanel);

                    // Show the levels screen after the usernames are saved
                    ChooseMpLevels.showMpLevels(gameWindow); // Call the levels class to show the next screen
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

        //  a new panel for the back button with BorderLayout
        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(Color.YELLOW);

        //  the back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        //  action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  back to the previous screen (Start screen or username screen)
                gameWindow.setVisible(false);  // Hide the current window
                JFrame startWindow = new JFrame("Start or Exit");
                new choosePlayers().showchoosePlayers(startWindow);
                startWindow.setSize(800, 600);
                startWindow.setResizable(true);
                startWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                startWindow.setVisible(true);
            }
        });

        backButtonPanel.add(backButton, BorderLayout.WEST);
        gameWindow.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);
        gameWindow.getContentPane().add(inputPanel, BorderLayout.CENTER);
        gameWindow.getContentPane().add(canvas, BorderLayout.NORTH);
        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();

        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);//علشان تظهر في نص الشاشه

        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
