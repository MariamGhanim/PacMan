package UIwindows.menu;

import UIwindows.menu.multiplayer.mpLevel1;
import UIwindows.menu.multiplayer.mpLevel2;
import UIwindows.PlayGame;
import UIwindows.menu.singleplayer.Level1;
import UIwindows.menu.singleplayer.Level2;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChooseMpLevels {
    public static void showMpLevels(JFrame gameWindow) {
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("Assets/screens/wall_2.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(null);

        GLCanvas canvas = new GLCanvas();
        gameWindow.setLayout(new BorderLayout());
        gameWindow.getContentPane().add(canvas, BorderLayout.CENTER);
        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);


        JButton level1Button = new JButton();
        ImageIcon level1Icon = new ImageIcon("Assets/level1.png");
        Image scaledLevel1Image = level1Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH);
        level1Button.setIcon(new ImageIcon(scaledLevel1Image));
        level1Button.setBorderPainted(true);
        level1Button.setContentAreaFilled(false);
        level1Button.setFocusPainted(true);

        JButton level2Button = new JButton();
        ImageIcon level2Icon = new ImageIcon("Assets/level2.png");
        Image scaledLevel2Image = level2Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH);
        level2Button.setIcon(new ImageIcon(scaledLevel2Image));
        level2Button.setBorderPainted(true);
        level2Button.setContentAreaFilled(false);
        level2Button.setFocusPainted(true);


        gameWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int windowWidth = gameWindow.getWidth();
                int windowHeight = gameWindow.getHeight();
                int centerX = (windowWidth / 2 )- 70;

                int startY = (int) (windowHeight * 0.3);
                int gap = (int) (windowHeight * 0.3);
                level1Button.setBounds(centerX,startY,120,70);
                level2Button.setBounds(centerX,startY+gap,120,70);


            }
        });
        menuPanel.add(level1Button);
        menuPanel.add(level2Button);

        level1Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel1.showMpLevel1(gameWindow); // go to mpLevel1
        });

        level2Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel2.showMpLevel2(gameWindow); // go to mpLevel2
        });
        gameWindow.pack();

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }
}
