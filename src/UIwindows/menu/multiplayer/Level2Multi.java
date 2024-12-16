package UIwindows.menu.multiplayer;

import com.sun.opengl.util.FPSAnimator;
import objects.Ghost;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Level2Multi extends JFrame {

    public static void main(String[] args) {
        new Level2Multi();
    }

    public Level2Multi() {
        GLCanvas glcanvas;
        FPSAnimator animator;


        AnimListener listener = new Level2MultiListener();
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