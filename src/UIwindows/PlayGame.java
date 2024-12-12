package UIwindows;

import UIwindows.menu.startOrExit;

import javax.swing.JFrame;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class PlayGame {

    public static void main(String[] args) {

        JFrame gameWindow = new JFrame("Pacman Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);

        // Show the main menu
        startOrExit.showMenu(gameWindow);

        // Make the game window visible
        gameWindow.setVisible(true);
    }

    public static class GameRenderer implements GLEventListener {


        @Override
        public void init(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClearColor(0, 0, 0, 1);  // Set the background color to black

        }

        @Override
        public void display(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        }

        @Override
        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            GL gl = drawable.getGL();
            gl.glViewport(0, 0, width, height);  // Set the viewport to cover the entire window
        }

        @Override
        public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    }
}
