package UIwindows.menu;
import UIwindows.PlayGame;
import UIwindows.menu.singleplayer.Level1;
import UIwindows.menu.singleplayer.Level2;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLevels {
    public static void showLevels(JFrame gameWindow) {
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\PacMan_New\\src\\Assets\\screens\\wall_2.jpg");
                Image backgroundImage = backgroundIcon.getImage();
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
        ImageIcon level1Icon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\PacMan_New\\src\\Assets\\level1.png");
        Image scaledLevel1Image = level1Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH); // Resize the image
        level1Button.setIcon(new ImageIcon(scaledLevel1Image));
        level1Button.setBorderPainted(true);
        level1Button.setContentAreaFilled(false); //  the button transparent
        level1Button.setFocusPainted(true);

        JButton level2Button = new JButton();
        ImageIcon level2Icon = new ImageIcon("E:\\CS304\\Labs\\PacMan_Project\\PacMan_New\\src\\Assets\\level2.png");
        Image scaledLevel2Image = level2Icon.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH); // Resize the image
        level2Button.setIcon(new ImageIcon(scaledLevel2Image));
        level2Button.setBorderPainted(true);
        level2Button.setContentAreaFilled(false);
        level2Button.setFocusPainted(true);

        //  bounds for buttons
        level1Button.setBounds(200, 280, 120, 70);
        level2Button.setBounds(450, 280, 120, 70);
        menuPanel.add(level1Button);
        menuPanel.add(level2Button);

        //  action listeners to buttons
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove current components
                gameWindow.getContentPane().removeAll();
                // Open Level1
                Level1.showLevel1(gameWindow);
            }
        });

        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove current components
                gameWindow.getContentPane().removeAll();
                // Open Level2
                Level2.showLevel2(gameWindow);
            }
        });

        // Set game window properties
        gameWindow.setSize(800, 600);
        gameWindow.setResizable(true); // Allow resizing
        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }
}
