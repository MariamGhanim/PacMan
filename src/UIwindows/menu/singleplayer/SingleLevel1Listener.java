package UIwindows.menu.singleplayer;
import com.sun.opengl.util.GLUT;
import texture.TextureReader;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javax.media.opengl.GL.GL_CURRENT_BIT;
import static javax.media.opengl.GL.GL_TEXTURE_2D;


public class  SingleLevel1Listener extends BaseJogl {

    ArrayList<Points> PointsList = new ArrayList<>();
    ArrayList<Points> FruitsList = new ArrayList<>();
    ArrayList<Texts> TextsList = new ArrayList<>();
    ArrayList<Integer> KeyList = new ArrayList<>();



    private final static String[] textureNames = {
            "pacman/r1.png", "pacman/r2.png", "pacman/r3.png",
            "pacman/l1.png", "pacman/l2.png", "pacman/l3.png",
            "pacman/t1.png", "pacman/t2.png", "pacman/t3.png",
            "pacman/b1.png", "pacman/b2.png", "pacman/b3.png",
            "extra/dot.png", "extra/apple.png", "Ready.png",
            "GameOver.png", "Win.png", "menu.jpg",
            "level1.png", "ghosts/blinky.png", "ghosts/pinky.png",
            "ghosts/clyde.png", "Background.jpeg"
    };


    public class PacObject {
        double x, y;
        int texture, index, random;

        PacObject(int texture, int index) {
            this.texture = texture;
            this.index = index;
        }

        PacObject(int texture, int index, int random) {
            this.texture = texture;
            this.index = index;
            this.random = random;
        }
    }


    public class Points {
        private int index;
        private double x, y;
        private int top, left, bottom, right;
        private boolean isChecked;

        private final double[][] initPoints = {
                {0, 0, 0, 0, 0, 0, 0},
                {1, 45, 38.75, -1, -1, 50, 2},
                {2, 61.25, 38.75, 3, 67, 1, -1},
                {3, 61.25, 48, 4, 2, -1, 17},
                {4, 61.25, 58, -1, 3, 5, -1},
                {5, 50.75, 58, 6, -1, 53, 4},
                {6, 50.75, 68, -1, 5, -1, 7},
                {7, 61.75, 68, 8, -1, 6, -1},
                {8, 61.75, 77.5, -1, 7, 9, 16},
                {9, 51, 77.5, 10, -1, 57, 8},
                {10, 51, 90.5, -1, 9, -1, 11},
                {11, 71.5, 90.5, -1, 16, 10, 12},
                {12, 89.25, 90.5, -1, 13, 11, -1},
                {13, 89.25, 77.5, 12, 14, 16, -1},
                {14, 89.25, 67.75, 13, -1, 15, -1},
                {15, 71.5, 67.75, 16, 17, -1, 14},
                {16, 71.5, 77.5, 11, 15, 8, 13},
                {17, 71.5, 48, 15, 19, 3, 18},
                {18, 90, 48, -1, -1, 17, -2},
                {19, 71.5, 29, 17, 30, 67, 20},
                {20, 90, 29, -1, 21, 19, -1},
                {21, 90, 19, 20, -1, 22, -1},
                {22, 82, 19, -1, 23, -1, 21},
                {23, 82, 10, 22, -1, 31, 24},
                {24, 90, 10, -1, 25, 23, -1},
                {25, 90, 0, 24, -1, 26, -1},
                {26, 50.5, 0, 27, -1, 38, 25},
                {27, 50.5, 10, -1, 26, -1, 28},
                {28, 61.5, 10, 29, -1, 27, -1},
                {29, 61.5, 19, -1, 28, 33, 30},
                {30, 71.5, 19, 19, 31, 29, -1},
                {31, 71.5, 10, 30, -1, -1, 23},
                {32, 51, 29, -1, 33, -1, 67},
                {33, 51, 19, 32, -1, 34, 29},
                {34, 40, 19, 49, -1, 35, 33},
                {35, 29, 19, -1, 36, 46, 34},
                {36, 29, 10, 35, -1, -1, 37},
                {37, 40, 10, -1, 38, 36, -1},
                {38, 40, 0, 37, -1, 39, 26},
                {39, 0, 0, 40, -1, -1, 38},
                {40, 0, 10, -1, 39, -1, 41},
                {41, 8, 10, 42, -1, 40, 47},
                {42, 8, 19, -1, 41, 43, -1},
                {43, 0, 19, 44, -1, -1, 42},
                {44, 0, 29, -1, 43, -1, 45},
                {45, 18, 29, 65, 46, 44, 48},
                {46, 18, 19, 45, 47, -1, 35},
                {47, 18, 10, 46, -1, 41, -1},
                {48, 29, 29, 50, -1, 45, 49},
                {49, 40, 29, -1, 34, 48, -1},
                {50, 29, 38.75, 51, 48, -1, 1},
                {51, 29, 48, 52, 50, 65, -1},
                {52, 29, 58, -1, 51, -1, 53},
                {53, 40, 58, 54, -1, 52, 5},
                {54, 40, 68, -1, 53, 55, -1},
                {55, 29, 68, 56, -1, -1, 54},
                {56, 29, 77.5, -1, 55, 64, 57},
                {57, 40, 77.5, 58, -1, 56, 9},
                {58, 40, 90.5, -1, 57, 59, -1},
                {59, 18, 90.5, -1, 64, 60, 58},
                {60, 0, 90.5, -1, 61, -1, 59},
                {61, 0, 77.5, 60, 62, -1, 64},
                {62, 0, 68, 61, -1, -1, 63},
                {63, 18, 68, 64, 65, 62, -1},
                {64, 18, 77.5, 59, 63, 61, 56},
                {65, 18, 48, 63, 45, 66, 51},
                {66, 0, 48, -1, -1, -2, 65},
                {67, 61.25, 29, 2, -1, 32, 19},
        };
        private final double[][] Fruits = {
                {78, 20, 0},
                {79, 69, 0},
                {80, 18, 59},
                {81, 71.5, 59},
                {82, 29, 90.5},
        };

        public Points() {
        }

        public Points(int index, double x, double y, int top, int bottom, int left, int right, boolean isChecked) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
            this.isChecked = isChecked;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public int getTop() {
            return top;
        }

        public int getLeft() {
            return left;
        }

        public int getBottom() {
            return bottom;
        }

        public int getRight() {
            return right;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getIndex() {
            return index;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public double[][] getInitPoints() {
            return initPoints;
        }

        public double[][] getInitFruits() {
            return Fruits;
        }
    }



    public class Texts {
        int index;
        boolean isAppear;

        public Texts(int index, boolean isAppear) {
            this.index = index;
            this.isAppear = isAppear;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isAppear() {
            return isAppear;
        }

        public void setAppear(boolean appear) {
            isAppear = appear;
        }
    }


    PacObject SingleLevel1Listener = new PacObject(19, 1);
    PacObject[] Enemies = {
            new PacObject(19, 20, 37 + (int) (Math.random() * 4)),
            new PacObject(20, 30, 37 + (int) (Math.random() * 4)),
            new PacObject(21, 40, 37 + (int) (Math.random() * 4)),
    };
    int[] Texts = {14, 15, 16};
    double[][] initPoints = new Points().getInitPoints();
    double[][] Fruits = new Points().getInitFruits();
    private final static int[] textures = new int[textureNames.length];
    TextureReader.Texture[] textureArr = new TextureReader.Texture[textureNames.length];
    private static final int BUFFER_SIZE = 4096, WIDTH = 100, Height = 100;
    private final double SPEED = 0.25;
    private boolean StartGame = true , GameOver = false; // انا عدلت هنا
    int keyCode, Level = 1, Angle = 0, Score = 0, FinalScore = 780, FaceAnimations = 0, Face = 0, n = 0;

    private void ResetPoints() {
        PointsList.clear();
        for (double[] points : initPoints) {
            PointsList.add(new Points((int) points[0], points[1], points[2], (int) points[3], (int) points[4], (int) points[5], (int) points[6], false));
        }

        FruitsList.clear();
        for (double[] points : Fruits) {
            FruitsList.add(new Points((int) points[0], points[1], points[2], -1, -1, -1, -1, false));
        }

        TextsList.clear();
        for (int T : Texts) {
            TextsList.add(new Texts(T, false));
        }
    }

    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glEnable(GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);
        for (int i = 0; i < textureNames.length; i++) {
            try {
                textureArr[i] = TextureReader.readTexture("Assets///" + textureNames[i], true);
                gl.glBindTexture(GL_TEXTURE_2D, textures[i]);
                new GLU().gluBuild2DMipmaps(GL_TEXTURE_2D, GL.GL_RGBA, textureArr[i].getWidth(), textureArr[i].getHeight(), GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, textureArr[i].getPixels());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reInit();
    }

    public void reInit() {
        ResetPoints();

        PlaySound("Assets\\sounds\\pacman_beginning.wav", 0);

        StartGame = true; // انا عدلت هنا
        GameOver = false;

        SingleLevel1Listener.index = 1;
        Angle = 0;
        FaceAnimations = 0;
        Face = 0;
        Score = 0;

        SingleLevel1Listener.x = PointsList.get(SingleLevel1Listener.index).getX() - 5;
        SingleLevel1Listener.y = PointsList.get(SingleLevel1Listener.index).getY();

        Enemies[0].index = 20;
        Enemies[1].index = 30;
        Enemies[2].index = 40;
        for (PacObject E : Enemies) {
            E.x = PointsList.get(E.index).getX();
            E.y = PointsList.get(E.index).getY();
        }
    }

    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        Angle += 2;

        if (StartGame) {
            if (killRange()) {
                KeyList.clear();
                GameOver = true;
                StartGame = false;
                n = 0;
            }

            DrawTexture(gl, textureNames.length - 1, new double[]{0, 0}, new double[]{1, 1});

            for (Points p : PointsList)
                if (!p.isChecked())
                    DrawTexture(gl, 12, new double[]{p.getX() / (WIDTH / 2.0) - 0.9, p.getY() / (Height / 2.0) - 0.9}, new double[]{0.075, 0.075});

            for (Points f : FruitsList)
                if (!f.isChecked())
                    DrawTexture(gl, 13, new double[]{f.getX() / (WIDTH / 2.0) - 0.9, f.getY() / (Height / 2.0) - 0.9}, new double[]{0.03, 0.03});

            for (Texts t : TextsList)
                if (t.isAppear)
                    DrawTexture(gl, t.getIndex(), new double[]{0, 0.07}, new double[]{0.17, 0.13});

            for (Points P : PointsList) {
                if (SingleLevel1Listener.x == P.getX() && SingleLevel1Listener.y == P.getY() && !P.isChecked()) {
                    PlaySound("Assets\\sounds\\pacman_chomp.wav", -1);
                    Score += 10;
                    P.setChecked(true);
                }
            }
            for (Points F : FruitsList) {
                if (SingleLevel1Listener.x == F.getX() && SingleLevel1Listener.y == F.getY() && !F.isChecked()) {
                    PlaySound("Assets\\sounds\\pacman_eatfruit.wav", -1);
                    Score += 20;
                    F.setChecked(true);
                }
            }

            DrawTexture(gl, Face + FaceAnimations % 3, new double[]{SingleLevel1Listener.x / (WIDTH / 2.0) - 0.9, SingleLevel1Listener.y / (Height / 2.0) - 0.9}, new double[]{0.05, 0.05});

            for (PacObject E : Enemies) {
                DrawTexture(gl, E.texture, new double[]{E.x / (WIDTH / 2.0) - 0.9, E.y / (Height / 2.0) - 0.9}, new double[]{0.05, 0.05});
            }

            UpdateScoreAndLevel(gl);
        } else {
            DrawTexture(gl, 17, new double[]{0, 0}, new double[]{1, 1});
            DrawTexture(gl, 18, new double[]{0, -0.6}, new double[]{0.3, 0.3});
        }

        if (KeyList.size() != 0) {
            handleKeyPress();
        }

        for (int i = 0; i < Level; i++) {
            handleKeyPressEnemy();
        }

        if (GameOver) {
            PlaySound("Assets\\sounds\\pacman_death.wav", 1);
            GameOver = false;
        }



        if (Score == FinalScore) {
            PlaySound("Assets\\sounds\\Victory.wav", 2);
            Score = 0;
        }
    }

    private void DrawTexture(GL g, int textureIdx, double[] position, double[] scale) {
        g.glEnable(GL.GL_BLEND);
        g.glBindTexture(GL_TEXTURE_2D, textures[textureIdx]);
        g.glPushMatrix();
        g.glTranslated(position[0], position[1], 0);
        g.glScaled(scale[0], scale[1], 1);
        if (textureIdx == 13) g.glRotated(Angle % 360, 0, 0, 1);
        g.glBegin(GL.GL_QUADS);
        g.glTexCoord2f(0.0f, 0.0f);
        g.glVertex3f(-1.0f, -1.0f, -1.0f);
        g.glTexCoord2f(1.0f, 0.0f);
        g.glVertex3f(1.0f, -1.0f, -1.0f);
        g.glTexCoord2f(1.0f, 1.0f);
        g.glVertex3f(1.0f, 1.0f, -1.0f);
        g.glTexCoord2f(0.0f, 1.0f);
        g.glVertex3f(-1.0f, 1.0f, -1.0f);
        g.glEnd();
        g.glPopMatrix();
        g.glDisable(GL.GL_BLEND);
    }

    public void UpdateScoreAndLevel(GL gl) {
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glDisable(GL_TEXTURE_2D);
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glColor4f(1, 0, 0, 0.5f);
        gl.glPushMatrix();
        GLUT glut = new GLUT();
        gl.glRasterPos2d(-0.1, 0.958);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Score : " + Score);
        gl.glRasterPos2d(-0.9, 0.958);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "LV : " + Level);
        gl.glPopMatrix();
        gl.glPopAttrib();
        gl.glEnable(GL_TEXTURE_2D);
    }

    private boolean killRange() {
        return (
                (Math.abs(SingleLevel1Listener.y - Enemies[0].y) <= 5 && Math.abs(SingleLevel1Listener.x - Enemies[0].x) <= 5) ||
                        (Math.abs(SingleLevel1Listener.y - Enemies[1].y) <= 5 && Math.abs(SingleLevel1Listener.x - Enemies[1].x) <= 5) ||
                        (Math.abs(SingleLevel1Listener.y - Enemies[2].y) <= 5 && Math.abs(SingleLevel1Listener.x - Enemies[2].x) <= 5)
        );
    }

    public void keyPressed(final KeyEvent event) {
        keyCode = event.getKeyCode();

        if (keyCode == 38 || keyCode == 37 || keyCode == 39 || keyCode == 40) {
            KeyList.add(keyCode);
        }
    }

    public void mouseClicked(MouseEvent e) {
//        double x = e.getX(), y = e.getY();
//
//        if ((355 < x && x < 485) && !StartGame) {
//            StartGame = true;
//
//            if (539 < y && y < 597)
//                Level = 1;
//            else if (624 < y && y < 680)
//                Level = 2;
//            else if (708 < y && y < 766)
//                Level = 3;
//            else
//                StartGame = false;
        //}

        if (!StartGame) {
            StartGame = true;
            Level = 1;  // تعيين المستوى الأول مباشرة
        }
    }

    public boolean isKeyPressed(int keyC) {
        return keyC == KeyList.get(n);
    }

    private void handleKeyPress() {
        int T = PointsList.get(SingleLevel1Listener.index).getTop();
        int B = PointsList.get(SingleLevel1Listener.index).getBottom();
        int L = PointsList.get(SingleLevel1Listener.index).getLeft();
        int R = PointsList.get(SingleLevel1Listener.index).getRight();

        if (isKeyPressed(38) && T != -1) {
            if (PointsList.get(T).getY() == SingleLevel1Listener.y) {
                checkN();
                SingleLevel1Listener.index = T;
            } else {
                SingleLevel1Listener.y += SPEED;
                Face = 6;
                FaceAnimations++;
            }
        } else if (isKeyPressed(40) && B != -1) {
            if (PointsList.get(B).getY() == SingleLevel1Listener.y) {
                checkN();
                SingleLevel1Listener.index = B;
            } else {
                SingleLevel1Listener.y -= SPEED;
                Face = 9;
                FaceAnimations++;
            }
        } else if (isKeyPressed(37) && L != -1) {
            if (L == -2) {
                SingleLevel1Listener.index = 18;
                SingleLevel1Listener.x = PointsList.get(SingleLevel1Listener.index).getX();
                SingleLevel1Listener.y = PointsList.get(SingleLevel1Listener.index).getY();
                return;
            }
            if (PointsList.get(L).getX() == SingleLevel1Listener.x) {
                checkN();
                SingleLevel1Listener.index = L;
            } else {
                SingleLevel1Listener.x -= SPEED;
                Face = 3;
                FaceAnimations++;
            }
        } else if (isKeyPressed(39) && R != -1) {
            if (R == -2) {
                SingleLevel1Listener.index = 66;
                SingleLevel1Listener.x = PointsList.get(SingleLevel1Listener.index).getX();
                SingleLevel1Listener.y = PointsList.get(SingleLevel1Listener.index).getY();
                return;
            }
            if (PointsList.get(R).getX() == SingleLevel1Listener.x) {
                checkN();
                SingleLevel1Listener.index = R;
            } else {
                SingleLevel1Listener.x += SPEED;
                Face = 0;
                FaceAnimations++;
            }
        } else checkN();
    }

    private void handleKeyPressEnemy() {
        for (PacObject E : Enemies) {
            if (E.random == 38) {
                int T = PointsList.get(E.index).getTop();
                if (T != -1) {
                    if (PointsList.get(T).getY() == E.y) {
                        E.random = 37 + (int) (Math.random() * 4);
                        E.index = T;
                    } else {
                        E.y += SPEED;
                    }
                } else
                    E.random = 37 + (int) (Math.random() * 4);
            } else if (E.random == 40) {
                int B = PointsList.get(E.index).getBottom();
                if (B != -1) {
                    if (PointsList.get(B).getY() == E.y) {
                        E.random = 37 + (int) (Math.random() * 4);
                        E.index = B;
                    } else {
                        E.y -= SPEED;
                    }
                } else
                    E.random = 37 + (int) (Math.random() * 4);
            } else if (E.random == 37) {
                int L = PointsList.get(E.index).getLeft();
                if (L == -2) {
                    E.index = 18;
                    E.x = PointsList.get(E.index).getX();
                    E.y = PointsList.get(E.index).getY();
                    return;
                }
                if (L != -1) {
                    if (PointsList.get(L).getX() == E.x) {
                        E.random = 37 + (int) (Math.random() * 4);
                        E.index = L;
                    } else {
                        E.x -= SPEED;
                    }
                } else
                    E.random = 37 + (int) (Math.random() * 4);
            } else if (E.random == 39) {
                int R = PointsList.get(E.index).getRight();
                if (R == -2) {
                    E.index = 66;
                    E.x = PointsList.get(E.index).getX();
                    E.y = PointsList.get(E.index).getY();
                    return;
                }
                if (R != -1) {
                    if (PointsList.get(R).getX() == E.x) {
                        E.random = 37 + (int) (Math.random() * 4);
                        E.index = R;
                    } else {
                        E.x += SPEED;
                    }
                } else
                    E.random = 37 + (int) (Math.random() * 4);
            }
        }
    }

    private void checkN() {
        if (n < KeyList.size() - 1) {
            n++;
        }
    }

    public synchronized void PlaySound(final String url, int idx) {
        new Thread(new Runnable() {
            public void run() {
                File soundFile = new File(url);
                try {
                    AudioInputStream sampleStream = AudioSystem.getAudioInputStream(soundFile);
                    AudioFormat formatAudio = sampleStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatAudio);
                    SourceDataLine theAudioLine = (SourceDataLine) AudioSystem.getLine(info);
                    theAudioLine.open(formatAudio);
                    theAudioLine.start();

                    if (idx != -1) {
                        TextsList.get(idx).setAppear(true);
                    }

                    byte[] bufferBytes = new byte[BUFFER_SIZE];
                    int readBytes = -1;

                    while ((readBytes = sampleStream.read(bufferBytes)) != -1) {
                        theAudioLine.write(bufferBytes, 0, readBytes);
                    }
                    theAudioLine.drain();
                    theAudioLine.close();
                    sampleStream.close();

                    if (idx != -1) {
                        TextsList.get(idx).setAppear(false);
                    }

                    if (idx == 1 || idx == 2) {
                        reInit();
                    }
                } catch (UnsupportedAudioFileException e) {
                    System.out.println("Unsupported file.");
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    System.out.println("Line not found.");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Experienced an error.");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


