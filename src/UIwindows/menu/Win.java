package UIwindows.menu;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Win extends JFrame {
    public Win() {
        // إعداد نافذة شاشة الفوز
        setTitle("You Win!");
        setSize(800, 600); // حجم النافذة
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        AnimationPanel animationPanel = new AnimationPanel();
        add(animationPanel);
        setVisible(true);
    }
    class AnimationPanel extends JPanel implements ActionListener {
        private Timer timer;
        private Image pacmanRightImage, pacmanLeftImage, ghost1Image, ghost2Image; // الصور
        private Image currentPacmanImage;
        private int pacmanX = 50, pacmanY = 250;
        private int ghost1X = 750, ghost1Y = 200;
        private int ghost2X = 750, ghost2Y = 300;
        private int pacmanSpeed = 5;
        private int ghost1Speed = -5;
        private int ghost2Speed = -6;
        private Clip winSound;
        public AnimationPanel() {

            pacmanRightImage = new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\imgs\\right.gif").getImage();
            pacmanLeftImage = new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\imgs\\left.gif").getImage();
            ghost1Image = new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\imgs\\inky.png").getImage();
            ghost2Image = new ImageIcon("C:\\Users\\IT\\PaccMan\\src\\Assets\\imgs\\pinky.png").getImage();
            currentPacmanImage = pacmanRightImage;

            try {
                File winSoundFile = new File("C:\\Users\\IT\\PaccMan\\src\\Assets\\sounds\\Victory.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(winSoundFile);
                winSound = AudioSystem.getClip();
                winSound.open(audioStream);
                winSound.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }


            timer = new Timer(30, this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(currentPacmanImage, pacmanX, pacmanY, 50, 50, this);
            g.drawImage(ghost1Image, ghost1X, ghost1Y, 50, 50, this);
            g.drawImage(ghost2Image, ghost2X, ghost2Y, 50, 50, this);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("YOU WIN!", 300, 100);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pacmanX += pacmanSpeed;
            if (pacmanX > getWidth() - 50 || pacmanX < 0) {
                pacmanSpeed = -pacmanSpeed;
                if (pacmanSpeed > 0) {
                    currentPacmanImage = pacmanRightImage;
                } else {
                    currentPacmanImage = pacmanLeftImage;
                }
            }
            ghost1X += ghost1Speed;
            ghost2X += ghost2Speed;
            if (ghost1X < -50) {
                ghost1Speed = -ghost1Speed;
            }
            if (ghost2X < -50) {
                ghost2Speed = -ghost2Speed;
            }
            repaint();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Win());
    }
}
