package UIwindows.menu;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOver extends JFrame {
    private Clip winSound;

    public GameOver() {
        setTitle("You Lose!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel winLabel = new JLabel();
        winLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winLabel.setVerticalAlignment(SwingConstants.CENTER);
        winLabel.setIcon(new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\GameOver.png"));
        try {
            File winSoundFile = new File("C:\\Users\\IT\\PaccMan\\src\\Assets\\sounds\\pacman_death.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(winSoundFile);
            winSound = AudioSystem.getClip();
            winSound.open(audioStream);
            winSound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        add(winLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameOver());
    }
}
