package UIwindows.menu.multiplayer;

import UIwindows.PlayGame;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class mpLevel2 extends JFrame {

    public static void showMpLevel2(JFrame gameWindow) {

        FPSAnimator animator;

        Level2MultiListener gameListener = new Level2MultiListener(gameWindow);

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
