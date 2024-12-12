package UIwindows.menu;

import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class twoLevels {
    public static void showTwoLevels(JFrame gameWindow) {
        // Create the OpenGL canvas
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());

        // Add the canvas to the JFrame
        gameWindow.getContentPane().add(canvas, BorderLayout.CENTER);

        // Revalidate the window to apply changes
        gameWindow.revalidate();
        gameWindow.repaint();

        // Start the OpenGL animator
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }
}
