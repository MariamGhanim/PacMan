package UIwindows;
import UIwindows.menu.startOrExit;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

public class PlayGame {
    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("Pacman Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);//علشان تظهر في نص الشاشه
        startOrExit.showMenu(gameWindow);
        gameWindow.setVisible(true);
    }

    public static class GameRenderer implements GLEventListener {
        @Override
        public void init(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClearColor(0, 0, 0, 1);

        }
        @Override
        public void display(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        }

        @Override
        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            GL gl = drawable.getGL();
            ((GL) gl).glViewport(0, 0, width, height);  // Set the viewport to cover the entire window
        }

        @Override
        public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    }
}
