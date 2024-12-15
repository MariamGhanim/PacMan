package UIwindows.menu;

import UIwindows.PlayGame;
import UIwindows.menu.choosePlayers;

import javax.media.opengl.GLCanvas;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class startOrExit {
    private static boolean isSoundPlayed = false;

    public static void showMenu(JFrame gameWindow) {
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!isSoundPlayed) {
                    playSound("src/Assets/sounds/pacman_beginning.wav");
                    isSoundPlayed = true;
                }

                ImageIcon backgroundIcon = new ImageIcon("Assets/titleScreen.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setLayout(new GridBagLayout());

        JButton startButton = new JButton("Start Game");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        JButton[] buttons = {startButton, helpButton, exitButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.YELLOW);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(200, 50));
        }

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(Box.createRigidArea(new Dimension(0, 280)), gbc);

        gbc.insets = new Insets(10, 280, 10, 280);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 1;
        menuPanel.add(startButton, gbc);

        gbc.gridy = 2;
        menuPanel.add(helpButton, gbc);

        gbc.gridy = 3;
        menuPanel.add(exitButton, gbc);

        startButton.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            choosePlayers.showchoosePlayers(gameWindow);
        });

        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(gameWindow,
                "Instructions:\n" +
                        "1. Use arrow keys to move Player 1 (PacMan 1).\n" +
                        "2. Use WASD keys to move Player 2 (PacMan 2).\n" +
                        "3. Avoid ghosts.\n" +
                        "4. Eat all the items to win.\n" +
                        "5. If a player gets caught by a ghost, they lose a life.\n" +
                        "6. The game ends when one player wins or both players lose all lives.\n",
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE));

        exitButton.addActionListener(e -> System.exit(0));

        gameWindow.getContentPane().removeAll();
        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);

        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            if (!file.exists()) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
