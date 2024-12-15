package UIwindows.menu.multiplayer;

import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Level2Multi extends JFrame {
    private JLabel scoreLabel;

    public static void main(String[] args) {
        new Level2Multi();
    }

    public Level2Multi() {
        GLCanvas glcanvas;
        FPSAnimator animator;

        scoreLabel = new JLabel("PacMan 1: 0  PacMan 2: 0");
        scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setOpaque(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(scoreLabel, BorderLayout.NORTH);

        AnimListener listener = new Level2MultiListener(scoreLabel);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);

        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Level 2 MultiPlayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

        glcanvas.requestFocus();
    }
}