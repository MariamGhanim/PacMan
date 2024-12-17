package UIwindows.menu.multiplayer;


import UIwindows.menu.startOrExit;
import com.sun.opengl.util.GLUT;
import logic.SoundManager;
import objects.Eating;
import objects.Ghost;
import objects.Pacman;
import texture.AnimListener;
import texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import static UIwindows.menu.twoUsername.userName1;
import static UIwindows.menu.twoUsername.userName2;
import static java.awt.event.KeyEvent.*;

public class LevelMulti1Listener extends AnimListener implements KeyListener , GLEventListener {

    int maxWidth = 600;
    int maxHeight = 600;
    int index1 = 0,index2=0;
    int x = 40,y = 540,x2=555,y2=60;
    ArrayList<Eating> eat = new ArrayList<Eating>();
    ArrayList<Eating> strawberry = new ArrayList<Eating>();
    ArrayList<Ghost> ghost = new ArrayList<>();
    Pacman pacman1 = new Pacman(x,y,index1);
    Pacman pacman2 = new Pacman(x2,y2,index2);
    int score1 = 0,score2=0,level=1;
    static String[] textureNames = {
            "pacman.png","up.gif","right.gif", "down.gif","left.gif",
            //5
            "strawberry.png","dot.png",
            //7
            "blinky.png","ghost.gif","pinky.png","clyde.png","inky.png","blue_ghost.png",
            "pause.png","Map3.jpg"
    };
    int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1,  1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1,1, 1, 1, 1, 1, 1,  1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
int rows = map.length;
    int column = map[0].length;
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    static int[] textures = new int[textureNames.length];
    GL gl;
    private JFrame gameWindow;

    public LevelMulti1Listener(JFrame gameWindow) {
        this.gameWindow = gameWindow;
    }
    public LevelMulti1Listener() {

    }

    public void UpdateScoreAndLevel(GL gl) {

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPushAttrib(GL.GL_CURRENT_BIT);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        GLUT glut = new GLUT();
        gl.glPushMatrix();
        gl.glRasterPos2d(-0.2, 0.95); // النص الخاص بـ Score
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24,
                userName1 + score1 + " | " + userName2 + ": " + score2);
        gl.glRasterPos2d(-0.9, 0.95); // النص الخاص بـ Level
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "LV: " + level);
        gl.glPopMatrix();
        gl.glPopAttrib();
        gl.glEnable(GL.GL_TEXTURE_2D);
    }


    public void init(GLAutoDrawable gld) {
        gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);
                new GLU().gluBuild2DMipmaps(GL.GL_TEXTURE_2D, GL.GL_RGBA,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, texture[i].getPixels());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addFood();
        ghost.add(new Ghost(300, 400, 7,1));
        ghost.add(new Ghost(575, 575, 8,1));
        ghost.add(new Ghost(70, 70, 9,1));
        ghost.add(new Ghost(500, 100, 9,1));
        for (Ghost g : ghost) {
            g.moveRandom();
        }
    }
    private void handelGhostMove() {
        for (Ghost g : ghost) {
            switch (g.getDirection()) {
                case -1 -> {
                }
                case 0 -> {
                    if (map[g.ConvertY() - 1][g.ConvertX()] == 0) {
                        g.moveRandom();
                        return;
                    }
                    g.moveUP();
                }
                case 1 -> {
                    if (map[g.ConvertY() + 1][g.ConvertX()] == 0) {
                        g.moveRandom();
                        return;
                    }
                    g.moveDown();
                }
                case 2 -> {
                    if (map[g.ConvertY()][g.ConvertX() + 1] == 0) {
                        g.moveRandom();
                        return;
                    }
                    g.moveRight();
                }
                case 3 -> {
                    if (map[g.ConvertY()][g.ConvertX() - 1] == 0) {
                        g.moveRandom();
                        return;
                    }
                    g.moveLeft();
                }
            }
        }
    }
    public void addFood(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if(i % 2 == 0 && j % 3 ==0) {
                    if(map[i][j] == 1 && i == j) strawberry.add(new Eating(j,i,5));
                    else if (map[i][j] == 1) {
                        eat.add(new Eating(j, i,6));
                    }
                }
            }
        }
    }
    public void DrawFood(GL gl){
        for(Eating e : eat)
            DrawSprite(e.ConvertX(),e.ConvertY(),e.getIndex(),1.0f);
        for(Eating s: strawberry)
            DrawSprite(s.ConvertX(),s.ConvertY(),s.getIndex(),0.6f);
    }
    public void handleEat(){
        for (int i = 0; i < eat.size(); i++) {
            boolean pelletEaten = false;

            if (pacman1.ConvertX() == eat.get(i).getX() && pacman1.ConvertY() == eat.get(i).getY()) {
                eat.remove(i);
                score1++;
                pelletEaten = true;
                SoundManager.playSoundOnce("src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (!pelletEaten && pacman2.ConvertX() == eat.get(i).getX() && pacman2.ConvertY() == eat.get(i).getY()) {
                eat.remove(i);
                score2++;
                pelletEaten = true;
                SoundManager.playSoundOnce("src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (pelletEaten) {
                i--;
                System.out.println(userName1 + " Score: " + score1 + " | " + userName2 + " Score: " + score2);
            }
        }
    }

    public void theWinner() {
        // Determine the winner when the food runs out
        if (eat.isEmpty()) {
            if (score1 > score2) {
                System.out.println("The winner is " + userName1);
            } else {
                System.out.println("The winner is " + userName2);
            }
        }
    }

    public void handleTheLose() {

        for (int i = 0; i < ghost.size(); i++) {
            if (pacman1.ConvertX() == ghost.get(i).ConvertX() && pacman1.ConvertY() == ghost.get(i).ConvertY()) {
                isPaused = true;
                announceWinner("PacMan 2 wins with a higher score!");
                return;
            }

            if (pacman2.ConvertX() == ghost.get(i).ConvertX() && pacman2.ConvertY() == ghost.get(i).ConvertY()) {
                isPaused = true;
                announceWinner("PacMan 1 wins with a higher score!");
                return;
            }
        }

        //  If all pellets are eaten (based on scores)
        if (eat.isEmpty()) {
            isPaused = true;
            if (score1 > score2) {
                announceWinner(userName1 + " wins with a score of " + score1 + "!");
            } else if (score2 > score1) {
                announceWinner(userName2 + " wins with a score of " + score2 + "!");
            } else {
                announceWinner("It's a tie! Both players scored " + score1 + "!");
            }
        }
    }

    private void announceWinner(String message) {
        SwingUtilities.invokeLater(() -> {

            JDialog dialog = new JDialog();
            dialog.setTitle("Game Over");
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);

            JLabel countdownLabel = new JLabel("<html>" + message + "<br>Leveling up in 5...</html>", SwingConstants.CENTER);
            countdownLabel.setVerticalAlignment(SwingConstants.CENTER);
            countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
            countdownLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dialog.add(countdownLabel);

            dialog.setVisible(true);


            SwingWorker<Void, Integer> countdownWorker = new SwingWorker<Void, Integer>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 5; i >= 0; i--) {
                        publish(i); // Update the countdown every second
                        Thread.sleep(1000); // Wait for 1 second
                    }
                    return null;
                }

                @Override
                protected void process(java.util.List<Integer> chunks) {
                    int timeRemaining = chunks.get(chunks.size() - 1);
                    // Update the label with the current countdown value
                    countdownLabel.setText("<html>" + message + "<br>Leveling up in " + timeRemaining + "...</html>");
                }

                @Override
                protected void done() {
                    dialog.dispose(); // Close the dialog
                    // Transition to the next level
                    gameWindow.getContentPane().removeAll();
                    mpLevel2.showMpLevel2(gameWindow);
                    gameWindow.revalidate();
                    gameWindow.repaint();
                }
            };

            // Start the countdown
            countdownWorker.execute();
        });
    }


    @Override
    public void display(GLAutoDrawable gld) {
        gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_DEPTH_TEST);
        SoundManager.stopSound("src/Assets/sounds/pacmanSong.wav");

        DrawBackground();
        UpdateScoreAndLevel(gl);
        DrawFood(gl);
        //draw two pacman
        DrawSprite(pacman1.getX(), pacman1.getY(), pacman1.getIndex(), 0.6f);
        DrawSprite(pacman2.getX(), pacman2.getY(), pacman2.getIndex(), 0.6f);
        drawGhost();
        if (isPaused) {
            DrawSprite(maxWidth / 2, maxHeight / 2, 13, 2.0f);
            return; // الخروج لمنع تنفيذ باقي الأكواد
        }
        handelGhostMove();
        handleKey();
        handleEat();
        theWinner();
        handleTheLose();

    }
    private void drawGhost() {
        for (Ghost g : ghost) {
            DrawSprite(g.getX(),g.getY(), g.getIndex(), g.getScale());
        }
    }
    public void DrawSprite(int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);

        gl.glPushMatrix();
        gl.glTranslatef((x - maxWidth / 2) / (float) maxWidth * 2,
                (y - maxHeight / 2) / (float) maxHeight * 2, 0);
        gl.glScalef(0.1f * scale, 0.1f * scale, 1);
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex2f(-1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex2f(1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex2f(1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex2f(-1.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawBackground() {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-1]);
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }
    public void handleKey(){
        if (isKeyPressed(VK_DOWN)){
            if (pacman1.getIndex() == 0) pacman1.setIndex(3);
            else pacman1.setIndex(0);
            if(map[pacman1.ConvertY() - 1][pacman1.ConvertX()] == 1) pacman1.setY(y -= 7);
        }
        if(isKeyPressed(VK_UP)){
            if (pacman1.getIndex() == 0) pacman1.setIndex(1);
            else pacman1.setIndex(0);
            if(map[pacman1.ConvertY() + 1][pacman1.ConvertX()] == 1) pacman1.setY(y += 7);
        }
        if(isKeyPressed(VK_RIGHT)){
            if (pacman1.getIndex() == 0) pacman1.setIndex(2);
            else pacman1.setIndex(0);
            if (x < maxWidth - 45){
                if(map[pacman1.ConvertY()][pacman1.ConvertX() + 1] == 1) pacman1.setX(x += 7);
            }
        }
        if(isKeyPressed(VK_LEFT)){
            if (pacman1.getIndex() == 0) pacman1.setIndex(4);
            else pacman1.setIndex(0);
            if(map[pacman1.ConvertY() ][pacman1.ConvertX() - 1] == 1) pacman1.setX(x -= 7);
        }
        //for the PacMan 2
        if(isKeyPressed(VK_W)){
            if(pacman2.getIndex() == 0) pacman2.setIndex(1);
            else pacman2.setIndex(0);
            if(map[pacman2.ConvertY() + 1][pacman2.ConvertX()] == 1) pacman2.setY( y2 += 7);
        }
        if(isKeyPressed(VK_S)){
            if(pacman2.getIndex() == 0) pacman2.setIndex(3);
            else pacman2.setIndex(0);
            if(map[pacman2.ConvertY() - 1][pacman2.ConvertX()] == 1) pacman2.setY( y2 -= 7);
        }
        if(isKeyPressed(VK_D)){
            if(pacman2.getIndex() == 0) pacman2.setIndex(2);
            else pacman2.setIndex(0);
            if(map[pacman2.ConvertY()][pacman2.ConvertX() + 1] == 1) pacman2.setX( x2 += 7);
        }
        if(isKeyPressed(VK_A)){
            if(pacman2.getIndex() == 0) pacman2.setIndex(4);
            else pacman2.setIndex(0);
            if(map[pacman2.ConvertY()][pacman2.ConvertX() - 1] == 1) pacman2.setX( x2 -= 7);
        }
    }
    private boolean isPaused = false;

    public BitSet keyBits = new BitSet(256);
    @Override
    public void keyPressed(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.set(keyCode);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isPaused = !isPaused;  // (true -> false or false -> true)
        }
    }
    @Override
    public void keyReleased(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
        pacman1.setIndex(0);
        pacman2.setIndex(0);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }



}