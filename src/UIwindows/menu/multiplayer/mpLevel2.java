package UIwindows.menu.multiplayer;

import UIwindows.PlayGame;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class mpLevel2 {
    public static void showMpLevel2(JFrame gameWindow) {
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PlayGame.GameRenderer());
        gameWindow.getContentPane().add(canvas, BorderLayout.CENTER);
        gameWindow.revalidate();
        gameWindow.repaint();
        new com.sun.opengl.util.FPSAnimator(canvas, 60).start();
    }
}
