package UIwindows.menu.multiplayer;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;

public class mpLevel1 {

    public static void showMpLevel1(JFrame gameWindow) {
        GLCanvas glcanvas;
        Animator animator;

        AnimListener listener = new LevelMulti1Listener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);


        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();


        gameWindow.getContentPane().removeAll();
        gameWindow.getContentPane().add(glcanvas, BorderLayout.CENTER);

        // Set the canvas size to fill the window
        glcanvas.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()));

        gameWindow.revalidate();
        gameWindow.repaint();
        gameWindow.setLocationRelativeTo(null);
        glcanvas.requestFocus();

    }

}
