package objects;

import java.awt.*;

public class Pacman {
    int x, y;
    int index;
    final float scale = 0.4f;


    public Pacman(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getScale() {
        return scale;
    }

    public int ConvertX() {
        return (x + 2) / 15 - 1;
    }

    public int ConvertY() {
        return (y + 8) / 15 - 1;
    }

    public Rectangle getBounds() {
        int width = (int) (30 * scale);
        int height = (int) (30 * scale);
        return new Rectangle(x, y, width, height);
    }
}
