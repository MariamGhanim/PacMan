package UIwindows.menu.singleplayer ;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.*;
import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class SingleLevel1 extends JFrame {

    GLCanvas glcanvas = new GLCanvas();

    Animator animator = new FPSAnimator(100);
    SingleLevel1Listener listener = new SingleLevel1Listener();

    public static void main(String[] args) {

        new SingleLevel1();
    }

    public SingleLevel1() {

        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);

        animator.add(glcanvas);
        getContentPane().add(glcanvas, BorderLayout.CENTER);

        setTitle("PAC-MAN-level1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 850);
        setLocationRelativeTo(null);
        setVisible(true);

        setFocusable(true);
        glcanvas.requestFocusInWindow();

        animator.start();
    }
}
