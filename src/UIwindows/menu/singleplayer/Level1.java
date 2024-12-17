package UIwindows.menu.singleplayer;


import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import logic.SoundManager;
import texture.AnimListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Level1 extends JFrame{

    public static void showLevel1(JFrame gameWindow) {

            FPSAnimator animator;

            SingleLevel1Listener gameListener = new SingleLevel1Listener(gameWindow);

            GLCanvas glcanvas = new GLCanvas();
            glcanvas.addGLEventListener(gameListener);
            glcanvas.addKeyListener(gameListener);

            animator = new FPSAnimator(15);
            animator.add(glcanvas);
            animator.start();

            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(glcanvas, BorderLayout.CENTER);


            glcanvas.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()));
            gameWindow.revalidate();
            gameWindow.repaint();
            gameWindow.setLocationRelativeTo(null);
            glcanvas.requestFocus();
        }
}
