package UIwindows.menu.multiplayer;
import com.sun.opengl.util.GLUT;
import objects.Eating;
import objects.Ghost;
import objects.Pacman;
import texture.AnimListener;
import texture.TextureReader;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import static java.awt.event.KeyEvent.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Level2MultiListener extends AnimListener implements KeyListener , GLEventListener {

    int maxWidth = 600;
    int maxHeight = 600;
    int index1 = 0,index2=0,index3=6,index4=7,index5=9 ,index6=10;
    int x = 30,y = 560,x2=560,y2=30,x3=300,y3=300,x4=300,y4=300,x5=340,y5=300,x6=330,y6=300;
    int lives = 3;
    ArrayList<Eating> eating = new ArrayList<Eating>();
    Pacman pacman1 = new Pacman(x,y,index1);
    Pacman pacman2 = new Pacman(x2,y2,index2);
    Ghost ghost1 = new Ghost(x3, y3, index3);
    Ghost ghost2 = new Ghost(x4, y4, index4);
    Ghost ghost3 = new Ghost(x5, y5, index5);

    Ghost ghost4 = new Ghost(x6, y6, index6);
    int score1 = 0,score2 = 0,level=2;


    static String[] textureNames = {
            "pacman.png","up.gif","right.gif", "down.gif","left.gif",
            //5
            "apple.png","blinky.png","pinky.png",
             "heart.png","clyde.png","Ghosts.png", "pause.png","Map.jpg",

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

    public Level2MultiListener() {

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
    }

    public void PacEat() {
        for (int i = 0; i < eating.size(); i++) {
            boolean pelletEaten = false;

            if (pacman1.ConvertX() == eating.get(i).getX() && pacman1.ConvertY() == eating.get(i).getY()) {
                eating.remove(i);
                score1++;
                pelletEaten = true;
                playSound("PAcMan/src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (!pelletEaten && pacman2.ConvertX() == eating.get(i).getX() && pacman2.ConvertY() == eating.get(i).getY()) {
                eating.remove(i);
                score2++;
                pelletEaten = true;
                playSound("src/Assets/sounds/pacman_eatfruit.wav");
            }

            if (pelletEaten) {
                i--;
                System.out.println("Pac1 Score: " + score1 + " Pac2 Score: " + score2);
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


    @Override
    public void display(GLAutoDrawable gld) {
        gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_DEPTH_TEST);

        DrawBackground();
        DrawFood(gl);
        UpdateScoreAndLevel(gl);
        DrawSprite(pacman1.getX(), pacman1.getY(), pacman1.getIndex(), 0.5f);
        DrawSprite(pacman2.getX(), pacman2.getY(), pacman2.getIndex(), 0.5f);

        DrawSprite(ghost1.getX(), ghost1.getY(), ghost1.getIndex(), 0.5f);
        DrawSprite(ghost2.getX(), ghost2.getY(), ghost2.getIndex(), 0.5f);
        DrawSprite(ghost3.getX(), ghost3.getY(), ghost3.getIndex(), 0.5f);
        DrawSprite(ghost4.getX(), ghost4.getY(), ghost4.getIndex(), 0.5f);


        handleKey();
        PacEat();
        if (isPaused) {
            DrawSprite(maxWidth / 2, maxHeight / 2, 11, 2.0f);
            return;
        }

        gl.glPopMatrix();
        System.out.println(pacman1.getX() + " " + pacman2.getX());
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
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Level: " + level);

        gl.glPopMatrix();
        gl.glPopAttrib();
        gl.glEnable(GL.GL_TEXTURE_2D);
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
            if (x < maxWidth - 45) {
                if (map[pacman1.ConvertY()][pacman1.ConvertX() + 1] == 1) pacman1.setX(x += 7);
            }
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

    private void playSound(String soundFile) {
        try {

            File file = new File(soundFile);
            if (!file.exists()) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}