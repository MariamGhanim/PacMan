import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

public class PlayGame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PacMan");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GLCanvas canvas = new GLCanvas();
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }
}
