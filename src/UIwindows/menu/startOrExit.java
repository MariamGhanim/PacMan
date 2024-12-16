package UIwindows.menu;

import UIwindows.PlayGame;
import UIwindows.menu.choosePlayers;
import logic.SoundManager;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class startOrExit {
    private static boolean isSoundOn = true; // للتحكم بحالة الصوت

    public static void showMenu(JFrame gameWindow) {
        GLCanvas canvas = new GLCanvas();

        // لوحة الخلفية
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // تشغيل الصوت لأول مرة
                if (!SoundManager.isSoundPlaying()) {
                    SoundManager.playSound("src/Assets/sounds/pacmanSong.wav");
                }


                ImageIcon backgroundIcon = new ImageIcon("Assets/titleScreen.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };


        menuPanel.setPreferredSize(new Dimension(800, 600));
        menuPanel.setLayout(null); // نظام تخطيط يدوي


        JButton soundToggleButton = new JButton(new ImageIcon("src/Assets/soundOn.png"));
        soundToggleButton.setBorderPainted(false);
        soundToggleButton.setContentAreaFilled(false);
        soundToggleButton.setFocusPainted(false);
        soundToggleButton.setPreferredSize(new Dimension(50, 50));

        ImageIcon soundOnIcon = new ImageIcon("src/Assets/soundOn.png");
        Image img = soundOnIcon.getImage();
        Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        soundToggleButton.setIcon(new ImageIcon(newImg));

        soundToggleButton.setBounds(560, 70, 200, 50);
        soundToggleButton.addActionListener(e -> {
            if (SoundManager.isSoundPlaying()) {
                SoundManager.stopSound("src/Assets/sounds/pacmanSong.wav");

                ImageIcon soundOffIcon = new ImageIcon("src/Assets/soundOff.png");
                Image imgOff = soundOffIcon.getImage();
                Image newImgOff = imgOff.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                soundToggleButton.setIcon(new ImageIcon(newImgOff));

            } else {

                if (!SoundManager.isSoundPlaying()) {
                    SoundManager.playSound("src/Assets/sounds/pacmanSong.wav");
                }
                ImageIcon soundOnIconUpdated = new ImageIcon("src/Assets/soundOn.png");
                Image imgOn = soundOnIconUpdated.getImage();
                Image newImgOn = imgOn.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                soundToggleButton.setIcon(new ImageIcon(newImgOn));

            }
        });


        soundToggleButton.setOpaque(false);
        soundToggleButton.setBorder(null);

        menuPanel.add(soundToggleButton);

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

        startButton.setBounds(300, 300, 200, 50);
        helpButton.setBounds(300, 380, 200, 50);
        exitButton.setBounds(300, 450, 200, 50);

        menuPanel.add(startButton);
        menuPanel.add(helpButton);
        menuPanel.add(exitButton);

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
                        "5. Space to pause.\n" +
                        "6. If a player gets caught by a ghost, they lose a life.\n" +
                        "7. The game ends when one player wins or both players lose all lives.\n",
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE));

        exitButton.addActionListener(e -> System.exit(0));

        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();


        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);
        gameWindow.revalidate();
        gameWindow.repaint();

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
