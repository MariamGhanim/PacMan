package UIwindows.menu.singleplayer;

import UIwindows.PlayGame;
import UIwindows.menu.multiplayer.LevelMulti1Listener;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import logic.SoundManager;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Level1 extends JFrame{

    public static void showLevel1(JFrame gameWindow) {
        GLCanvas glcanvas = new GLCanvas();
        SoundManager.stopSound("src/Assets/sounds/pacmanSong.wav");

        Animator animator = new FPSAnimator(100);
        SingleLevel1Listener listener = new SingleLevel1Listener();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);

        animator.add(glcanvas);
        gameWindow.getContentPane().add(glcanvas, BorderLayout.CENTER);

        gameWindow.setTitle("PAC-MAN-level1");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(600, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gameWindow.setFocusable(true);
        glcanvas.requestFocusInWindow();
        animator.start();
    }
}
