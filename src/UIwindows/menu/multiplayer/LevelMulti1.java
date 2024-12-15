package UIwindows.menu.multiplayer;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class LevelMulti1 extends JFrame {
    private JLabel scoreLabel;

    public static void main(String[] args) {
        new LevelMulti1();
    }


    public LevelMulti1() {
        GLCanvas glcanvas;
        Animator animator;
        scoreLabel = new JLabel("PacMan 1: 0 PacMan2:0");
        scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setOpaque(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(scoreLabel, BorderLayout.NORTH);

        AnimListener listener = new LevelMulti1Listener(scoreLabel);

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Level 1 MultiPlayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
