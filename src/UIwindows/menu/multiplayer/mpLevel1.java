package UIwindows.menu.multiplayer;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

public class mpLevel1 {

    public static void showMpLevel1(JFrame gameWindow) {
        // إنشاء Animator وتهيئة GLCanvas
        Animator animator;
        GLCanvas canvas = new GLCanvas();
        LevelMulti1Listener levelListener = new LevelMulti1Listener(); // Create the LevelMulti1Listener
        canvas.addGLEventListener(levelListener); // Adding LevelMulti1Listener instead of PlayGame.GameRenderer
        canvas.addKeyListener(levelListener);
        canvas.requestFocusInWindow(); // Ensure the canvas captures keyboard input

        // إزالة أي محتوى حالي من النافذة
        gameWindow.getContentPane().removeAll();
        gameWindow.getContentPane().add(canvas, BorderLayout.CENTER);

        // Set the canvas size to fill the window
        canvas.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()));

        // إعادة ضبط نافذة اللعبة
        gameWindow.revalidate();
        gameWindow.repaint();

        // إعداد وإطلاق Animator بمعدل إطارات 60 إطار/ثانية
        FPSAnimator fpsAnimator = new FPSAnimator(canvas, 60);
        fpsAnimator.start();

        animator = new Animator();
        animator.add(canvas);
        animator.start();

        // إعادة تمركز النافذة في وسط الشاشة
        gameWindow.setLocationRelativeTo(null);
    }
}
