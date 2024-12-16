package logic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip clip;

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // to loop
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void playSoundOnce(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void stopSound(String filePath) {
        if (clip != null && clip.isRunning()) {
            clip.stop();

        }
    }

    public static void resumeSound(String filePath) {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }

    }
    public static boolean isSoundPlaying() {
        if (clip != null && clip.isRunning()) {
            return true;  // الصوت شغال
        }
        return false;  // الصوت متوقف
    }


    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();

    }

}}
