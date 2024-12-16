package UIwindows.menu;
import javax.swing.*;
import java.awt.*;
public class GameOver extends JFrame {
    public GameOver() {
        setTitle("You Win!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel winLabel = new JLabel();
        winLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winLabel.setVerticalAlignment(SwingConstants.CENTER);
        winLabel.setIcon(new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\GameOver.png"));
        add(winLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameOver());
    }
}
