package UIwindows.menu.multiplayer;

import UIwindows.PlayGame;
import com.sun.opengl.util.FPSAnimator;
import texture.AnimListener;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class mpLevel1 extends JFrame {

    public static void showMpLevel1(JFrame gameWindow) {
        GLCanvas glcanvas;
        FPSAnimator animator;

        AnimListener listener = new LevelMulti1Listener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);

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
