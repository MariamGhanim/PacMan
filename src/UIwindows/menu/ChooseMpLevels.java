package UIwindows.menu;

import UIwindows.menu.multiplayer.mpLevel1;
import UIwindows.menu.multiplayer.mpLevel2;
import UIwindows.PlayGame;

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
        level1Button.setIcon(createScaledIcon(level1Icon, gameWindow));
        level1Button.setBorderPainted(false);
        level1Button.setContentAreaFilled(false);
        level1Button.setFocusPainted(true);

        JButton level2Button = new JButton();
        ImageIcon level2Icon = new ImageIcon("Assets/level2.png");
        level2Button.setIcon(createScaledIcon(level2Icon, gameWindow));
        level2Button.setBorderPainted(false);
        level2Button.setContentAreaFilled(false);
        level2Button.setFocusPainted(true);

        menuPanel.add(level1Button);
        menuPanel.add(level2Button);


        positionButtons(gameWindow, level1Button, level2Button);

        // action listeners for buttons
        level1Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel1.showMpLevel1(gameWindow);
        });

        level2Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel2.showMpLevel2(gameWindow);
        });


        gameWindow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                positionButtons(gameWindow, level1Button, level2Button);
            }
        });

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.revalidate();
        gameWindow.repaint();
        gameWindow.setLocationRelativeTo(null);

        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }


    private static ImageIcon createScaledIcon(ImageIcon icon, JFrame gameWindow) {
        int newWidth = (int)(gameWindow.getWidth() * 0.24);
        int newHeight = (int)(gameWindow.getHeight() * 0.15);
        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }


    private static void positionButtons(JFrame gameWindow, JButton level1Button, JButton level2Button) {
        level1Button.setBounds((int)(gameWindow.getWidth() * 0.25), (int)(gameWindow.getHeight() * 0.45),
                (int)(gameWindow.getWidth() * 0.15), (int)(gameWindow.getHeight() * 0.12));
        level2Button.setBounds((int)(gameWindow.getWidth() * 0.55), (int)(gameWindow.getHeight() * 0.45),
                (int)(gameWindow.getWidth() * 0.15), (int)(gameWindow.getHeight() * 0.12));
    }
}
