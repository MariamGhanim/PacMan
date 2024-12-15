package objects;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ghost {
    int maxWidth = 600;
    int maxHeight = 600;
    int x, y;
    int index;
    final float scale = 0.4f;
    Random random = new Random();

    private static Set<String> walls = new HashSet<>();

    public Ghost(int x, int y, int index) {
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


}
