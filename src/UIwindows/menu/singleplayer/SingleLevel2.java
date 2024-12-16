package UIwindows.menu.singleplayer;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.*;
import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class SingleLevel2 extends JFrame {

    GLCanvas glcanvas = new GLCanvas();

    Animator animator = new FPSAnimator(100);
    SingleLevel2Listener listener = new SingleLevel2Listener();

    public static void main(String[] args) {

        new SingleLevel2();
    }

    public SingleLevel2() {

        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);

        animator.add(glcanvas);
        getContentPane().add(glcanvas, BorderLayout.CENTER);

        setTitle("PAC-MAN-level2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        setFocusable(true);
        glcanvas.requestFocusInWindow();

        animator.start();
    }
}
