package UIwindows.menu.singleplayer;

import UIwindows.PlayGame;
import UIwindows.menu.multiplayer.LevelMulti1Listener;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Level2 extends JFrame{
    public static void showLevel2(JFrame gameWindow) {

        GLCanvas glcanvas = new GLCanvas();

        Animator animator = new FPSAnimator(100);
        SingleLevel2Listener listener = new SingleLevel2Listener();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);

        animator.add(glcanvas);
        gameWindow.getContentPane().add(glcanvas, BorderLayout.CENTER);

        gameWindow.setTitle("PAC-MAN-level2");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(600, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gameWindow.setFocusable(true);
        glcanvas.requestFocusInWindow();
        animator.start();
    }
}
