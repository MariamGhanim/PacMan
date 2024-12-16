package UIwindows;
import UIwindows.menu.startOrExit;
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

}
