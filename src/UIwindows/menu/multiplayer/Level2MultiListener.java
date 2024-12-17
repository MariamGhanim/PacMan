package UIwindows.menu.multiplayer;
import UIwindows.menu.startOrExit;
import com.sun.opengl.util.GLUT;
import logic.SoundManager;

import javax.swing.*;

import objects.Eating;
import objects.Ghost;
import objects.Pacman;
import texture.AnimListener;
import texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import static java.awt.event.KeyEvent.*;


public class Level2MultiListener extends AnimListener implements KeyListener , GLEventListener {
    private JFrame gameWindow;

    int maxWidth = 600;
    int maxHeight = 600;
    int index1 = 0,index2=0;
    int x = 30,y = 560,x2=560,y2=30;
    ArrayList<Eating> eating = new ArrayList<Eating>();
    ArrayList<Ghost> ghost = new ArrayList<>();
    Pacman pacman1 = new Pacman(x,y,index1);
    Pacman pacman2 = new Pacman(x2,y2,index2);
    int score1 = 0,score2 = 0,level=2;

    static String[] textureNames = {
            "pacman.png","up.gif","right.gif", "down.gif","left.gif",
            //5
            "apple.png","blinky.png","pinky.png"
             ,"clyde.png", "inky.png","blue_ghost.png","pause.png","Map.jpg",

    };

    int[][]map = new int [][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 0,  0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    };


    int rows = map.length;
    int column = map[0].length;

    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    static int[] textures = new int[textureNames.length];
    GL gl;

    public Level2MultiListener(JFrame gameWindow) {
        this.gameWindow = gameWindow;
    }
    public Level2MultiListener( ) {

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
        addApples();
        ghost.add(new Ghost(300, 400, 6,2));
        ghost.add(new Ghost(575, 575, 7,2));
        ghost.add(new Ghost(70, 70, 8,2));
        ghost.add(new Ghost(500, 100, 9,2));
        for (Ghost g : ghost) {
            g.moveRandom();
        }
    }

    public void PacEat() {
        for (int i = 0; i < eating.size(); i++) {
            boolean pelletEaten = false;

            if (pacman1.ConvertX() == eating.get(i).getX() && pacman1.ConvertY() == eating.get(i).getY()) {
                eating.remove(i);
                score1++;
                pelletEaten = true;
                SoundManager.playSoundOnce("src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (!pelletEaten && pacman2.ConvertX() == eating.get(i).getX() && pacman2.ConvertY() == eating.get(i).getY()) {
                eating.remove(i);
                score2++;
                pelletEaten = true;
                SoundManager.playSoundOnce("src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (pelletEaten) {
                i--;
                System.out.println("Pac1 Score: " + score1 + " Pac2 Score: " + score2);
            }
        }
    }
    private void handelGhostMove() {
        for (Ghost g : ghost) {
            g.move();
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
    public void theWinner(){
        if(eating.isEmpty()){
            if(score1 > score2){
                System.out.println("The winner is PacMan 1");
                // show that player1 is win
            }else{
                System.out.println("The winner is PacMan 2");

            }
        }
    }
    public void handleTheLose() {
        //  for collision with ghosts (PacMan loses)
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

        //  if all pellets are eaten (based on scores)
        if (eating.isEmpty()) {
            isPaused = true;
            if (score1 > score2) {
                announceWinner("PacMan 1 wins with a score of " + score1 + "!");
            } else if (score2 > score1) {
                announceWinner("PacMan 2 wins with a score of " + score2 + "!");
            } else {
                announceWinner("It's a tie! Both players scored " + score1 + "!");
            }
        }
    }

    private void announceWinner(String message) {
        SwingUtilities.invokeLater(() -> {
            int choice = JOptionPane.showOptionDialog(null,
                    message + "\nDo you want to go home or exit?",
                    "Game Over",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Home", "Exit"},
                    "Home");

            if (choice == 0) {
                gameWindow.getContentPane().removeAll();

                startOrExit.showMenu(gameWindow); //  back to the main menu
                gameWindow.revalidate();
                gameWindow.repaint();
            } else if (choice == 1) {
                System.exit(0); // Exit the game
            }
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
        DrawFood(gl);
        UpdateScoreAndLevel(gl);
        DrawSprite(pacman1.getX(), pacman1.getY(), pacman1.getIndex(), 0.5f);
        DrawSprite(pacman2.getX(), pacman2.getY(), pacman2.getIndex(), 0.5f);
        drawGhost();
        if (isPaused) {
            DrawSprite(maxWidth / 2, maxHeight / 2, 11, 2.0f);
            return;
        }
        handelGhostMove();
        handleKey();
        PacEat();

        theWinner();
        handleTheLose();
    }

    public void addApples() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    if (map[i][j] == 1) {
                        eating.add(new Eating(j, i, 5));
                    }
                }
            }
        }
    }



    public void UpdateScoreAndLevel(GL gl) {
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPushAttrib(GL.GL_CURRENT_BIT);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GLUT glut = new GLUT();
        gl.glPushMatrix();
        gl.glRasterPos2d(-0.2, 0.95);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24,
                "Score1: " + score1 + " |  Score2: " + score2);

        gl.glRasterPos2d(-0.9, 0.95);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "LV: " + level);

        gl.glPopMatrix();
        gl.glPopAttrib();
        gl.glEnable(GL.GL_TEXTURE_2D);
    }
    private void drawGhost() {
        for (Ghost g : ghost) {
            DrawSprite(g.getX(),g.getY(), g.getIndex(), g.getScale());
        }
    }
    public void DrawFood(GL gl){
        for(Eating e : eating)
            DrawSprite(e.ConvertX(),e.ConvertY(),e.getIndex(),0.4f);
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

        if (isKeyPressed(VK_DOWN)) {
            if (pacman1.getIndex() == 0) pacman1.setIndex(3);
            else pacman1.setIndex(0);
            if (map[pacman1.ConvertY() - 1][pacman1.ConvertX()] == 1) pacman1.setY(y -= 7);
        }
        if (isKeyPressed(VK_UP)) {
            if (pacman1.getIndex() == 0) pacman1.setIndex(1);
            else pacman1.setIndex(0);
            if (map[pacman1.ConvertY() + 1][pacman1.ConvertX()] == 1) pacman1.setY(y += 7);
        }
        if (isKeyPressed(VK_RIGHT)) {
            if (pacman1.getIndex() == 0) pacman1.setIndex(2);
            else pacman1.setIndex(0);
            if (map[pacman1.ConvertY()][pacman1.ConvertX() + 1] == 1) pacman1.setX(x += 7);
        }
        if (isKeyPressed(VK_LEFT)) {
            if (pacman1.getIndex() == 0) pacman1.setIndex(4);
            else pacman1.setIndex(0);
            if (map[pacman1.ConvertY()][pacman1.ConvertX() - 1] == 1) pacman1.setX(x -= 7);
        }
        //for the PacMan 2
        if (isKeyPressed(VK_W)) {
            if (pacman2.getIndex() == 0) pacman2.setIndex(1);
            else pacman2.setIndex(0);
            if (map[pacman2.ConvertY() + 1][pacman2.ConvertX()] == 1) pacman2.setY(y2 += 7);
        }
        if (isKeyPressed(VK_S)) {
            if (pacman2.getIndex() == 0) pacman2.setIndex(3);
            else pacman2.setIndex(0);
            if (map[pacman2.ConvertY() - 1][pacman2.ConvertX()] == 1) pacman2.setY(y2 -= 7);
        }
        if (isKeyPressed(VK_D)) {
            if (pacman2.getIndex() == 0) pacman2.setIndex(2);
            else pacman2.setIndex(0);
            if (map[pacman2.ConvertY()][pacman2.ConvertX() + 1] == 1) pacman2.setX(x2 += 7);
        }
        if (isKeyPressed(VK_A)) {
            if (pacman2.getIndex() == 0) pacman2.setIndex(4);
            else pacman2.setIndex(0);
            if (map[pacman2.ConvertY()][pacman2.ConvertX() - 1] == 1) pacman2.setX(x2 -= 7);
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

