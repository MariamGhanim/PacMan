package objects;

public class Pacman {
    int x,y;
    int index;
    final float scale = 0.4f;

    public int getIndex() {
        return index;
    }

    public Pacman(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
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

    public float getScale() {
        return scale;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int ConvertX(){
        return (x+2)/15 - 1;
    }
    public int ConvertY(){
        return (y+8)/15 - 1;
    }
}
