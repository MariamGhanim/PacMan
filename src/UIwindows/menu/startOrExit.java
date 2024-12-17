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
                ImageIcon backgroundIcon = new ImageIcon("Assets/titleScreen.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setPreferredSize(new Dimension(800, 600));
        menuPanel.setLayout(null);
        SoundManager.playSound("src/Assets/sounds/pacmanSong.wav");

        JButton soundToggleButton = new JButton(new ImageIcon("src/Assets/soundOn.png"));
        soundToggleButton.setBorderPainted(false);
        soundToggleButton.setContentAreaFilled(false);
        soundToggleButton.setFocusPainted(false);
        soundToggleButton.setPreferredSize(new Dimension(50, 50));

        ImageIcon soundOnIcon = new ImageIcon("src/Assets/soundOn.png");
        Image img = soundOnIcon.getImage();
        Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        soundToggleButton.setIcon(new ImageIcon(newImg));

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
            menuPanel.add(button);
        }


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
                        "6. If a player gets caught by a ghost, they lose a life.\n"
                        ,
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE));

        exitButton.addActionListener(e -> System.exit(0));

        soundToggleButton.addActionListener(e -> {
            if (isSoundOn) {
                SoundManager.stopSound("src/Assets/sounds/pacmanSong.wav");
                ImageIcon soundOffIcon = new ImageIcon("src/Assets/soundOff.png");
                Image imgOff = soundOffIcon.getImage();
                Image newImgOff = imgOff.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                soundToggleButton.setIcon(new ImageIcon(newImgOff));
                isSoundOn = false;
            } else {
                if (!SoundManager.isSoundPlaying()) {
                    SoundManager.playSound("src/Assets/sounds/pacmanSong.wav");
                }
                ImageIcon soundOnIconUpdated = new ImageIcon("src/Assets/soundOn.png");
                Image imgOn = soundOnIconUpdated.getImage();
                Image newImgOn = imgOn.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                soundToggleButton.setIcon(new ImageIcon(newImgOn));
                isSoundOn = true;
            }
        });


        gameWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int windowWidth = gameWindow.getWidth();
                int windowHeight = gameWindow.getHeight();
                int centerX = windowWidth / 2 - 100;

                int startY = (int) (windowHeight * 0.6);
                int gap = (int) (windowHeight * 0.1);


                startButton.setBounds(centerX, startY, 200, 50);
                helpButton.setBounds(centerX, startY + gap, 200, 50);
                exitButton.setBounds(centerX, startY + 2 * gap, 200, 50);
                int soundX = (int) (windowWidth * 0.15);
                int soundY = (int) (windowHeight * 0.20);
                soundToggleButton.setBounds(soundX, soundY, 50, 50);
            }
        });



        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);
        gameWindow.pack();

        gameWindow.revalidate();
        gameWindow.repaint();


        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
