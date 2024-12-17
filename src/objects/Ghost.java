package objects;

import java.util.Random;

public class Ghost {
    Random random = new Random();
    public int x, y,index;
    float scale = 0.5f;
    int ghostdir =-1;
    boolean isChase=false;
    boolean isScatter=false;
    boolean isFrightened=false;
    boolean isEaten=false;
    int targetX, targetY;
    long modeStartTime = 0;
    int level;
    int scattredTime = 5;
    int chaseTime = 30;
    int frighteningTime = 4;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Ghost(int x, int y , int index,int level) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.level=level;
        if (level==2){
            this.scattredTime = 5;
            this.frighteningTime = 4;
            this.chaseTime = 30;
        }
    }
    public void switchMode(boolean chase, boolean scatter, boolean frightened) {
        this.isChase = chase;
        this.isScatter = scatter;
        this.isFrightened = frightened;
        this.modeStartTime = System.currentTimeMillis();
    }
    public void moveScatter() {
        if (System.currentTimeMillis() - modeStartTime <= chaseTime * 1000) {
            switch (index) {
                case 0 -> { targetX = 15; targetY = 0; }
                case 1 -> { targetX = 0; targetY = 0; }
                case 2 -> { targetX = 0; targetY = 15; }
                case 3 -> { targetX = 15; targetY = 15; }
            }
        }
    }

    public void moveRandom() {
        int dir = random.nextInt(4);
        switch (dir) {
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

    public void moveFrightened() {
        if (System.currentTimeMillis() - modeStartTime <= frighteningTime * 1000) {
            moveRandom();
        } else {
            resetToStartPosition();
        }
    }
    public void resetToStartPosition() {
        this.x = 10;
        this.y = 10;
        this.isEaten = false;
    }
    public void eatenByPacMan() {
        this.isEaten = true;
        resetToStartPosition();
    }
    public void move() {
        if (isFrightened) {
            moveFrightened();
        } else if (isChase) {
            moveChase();
        } else if (isScatter) {
            moveScatter();
            moveRandom();
        }


        long elapsedTime = System.currentTimeMillis() - modeStartTime;

        if (isChase && elapsedTime > chaseTime * 1000) {
            switchMode(false, true, false);
        }
        else if (isFrightened && elapsedTime > frighteningTime * 1000) {
            switchMode(false, true, false);
        }


    }
    public void moveChase() {
        if (index == 6) {

            targetX = 15; targetY = 0;
        }
        else if (index == 7) {

            targetX = 5; targetY = 0;
        }
        else if (index == 2) {
            targetX = 10; targetY = 15;
        }
        else if (index == 3) {

            targetX = 0; targetY = 10;
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
    public boolean isEaten() {
        return isEaten;
    }
    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }
    public long getModeStartTime() {
        return modeStartTime;
    }
    public void setModeStartTime(long modeStartTime) {
        this.modeStartTime = modeStartTime;
    }
    public boolean isChase() {
        return isChase;
    }

    public boolean isFrightened() {
        return isFrightened;
    }

    public boolean isScatter() {
        return isScatter;
    }
}
