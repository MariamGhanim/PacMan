package objects;

import java.util.Random;
public class Ghost {
    Random random = new Random();
    public int x, y,index;
    float scale = 0.5f;
    int ghostdir =-1;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Ghost(int x, int y , int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public void randMove() {
        int sw = random.nextInt(4);
        switch (sw) {
            case 0 -> {
                ghostdir = 0;
            }
            case 1 -> {
                ghostdir = 1;

            }
            case 2 -> {
                ghostdir = 2;
            }
            case 3 -> {
                ghostdir = 3;
            }
        }
    }

    public void moveUP() {
        ghostdir = 0;
        y -= 5;
    }

    public void moveDown() {
        ghostdir = 1;
        y += 5;
    }

    public void moveRight() {
        ghostdir = 2;
        x += 5;
    }

    public void moveLeft() {
        ghostdir = 3;
        x -= 5;
    }
    public int ConvertX(){
        return (x+2)/15 - 1;
    }
    public int ConvertY(){
        return (y+8)/15 - 1;
    }
    public Random getRandom() {
        return random;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public int getDirection() {
        return ghostdir;
    }
}
