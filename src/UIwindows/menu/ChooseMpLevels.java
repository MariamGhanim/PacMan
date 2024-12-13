package UIwindows.menu;

import UIwindows.menu.multiplayer.mpLevel1;
import UIwindows.menu.multiplayer.mpLevel2;
import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class ChooseMpLevels {
    public static void showMpLevels(JFrame gameWindow) {
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\src\\Assets\\screens\\wall_2.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                // i scale  the image to fit the panel size
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setLayout(null);

        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        gameWindow.setLayout(new BorderLayout());
        gameWindow.getContentPane().add(canvas, BorderLayout.CENTER);
        gameWindow.getContentPane().add(menuPanel, BorderLayout.CENTER);

        //  image buttons for level1 and level2
        JButton level1Button = new JButton();
        ImageIcon level1Icon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\src\\Assets\\level1.png");
        Image scaledLevel1Image = level1Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH); // Resize the image
        level1Button.setIcon(new ImageIcon(scaledLevel1Image));
        level1Button.setBorderPainted(true);
        level1Button.setContentAreaFilled(false);
        level1Button.setFocusPainted(true);

        JButton level2Button = new JButton();
        ImageIcon level2Icon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\src\\Assets\\level2.png");
        Image scaledLevel2Image = level2Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH); // Resize the image
        level2Button.setIcon(new ImageIcon(scaledLevel2Image));
        level2Button.setBorderPainted(true);
        level2Button.setContentAreaFilled(false);
        level2Button.setFocusPainted(true);

        // button positions
        level1Button.setBounds(200, 280, 120, 70);
        level2Button.setBounds(450, 280, 120, 70);

        // action listeners
        level1Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel1.showMpLevel1(gameWindow); // go to mpLevel1
        });

        level2Button.addActionListener(e -> {
            gameWindow.getContentPane().removeAll();
            mpLevel2.showMpLevel2(gameWindow); // go to mpLevel2
        });

        // Add buttons to the panel
        menuPanel.add(level1Button);
        menuPanel.add(level2Button);

        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true);
        gameWindow.revalidate();
        gameWindow.repaint();

        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }
}
