package UIwindows.menu.multiplayer;

import UIwindows.PlayGame;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class mpLevel2 extends JFrame {

    public static void showMpLevel2(JFrame gameWindow) {
        GLCanvas glcanvas;
        FPSAnimator animator;


        JLabel scoreLabel = new JLabel("PacMan 1: 0  PacMan 2: 0");
        scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setOpaque(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);


        AnimListener listener = new Level2MultiListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);

        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        gameWindow.getContentPane().removeAll();
        gameWindow.getContentPane().add(scoreLabel, BorderLayout.NORTH);
        gameWindow.getContentPane().add(glcanvas, BorderLayout.CENTER);


        glcanvas.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()));
        gameWindow.revalidate();
        gameWindow.repaint();
        gameWindow.setLocationRelativeTo(null);
        glcanvas.requestFocus();
    }
}
