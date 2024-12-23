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
        gameWindow.setLocationRelativeTo(null);
        startOrExit.showMenu(gameWindow);
        gameWindow.setVisible(true);
    }
}
