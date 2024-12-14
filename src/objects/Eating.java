package objects;

public class Eating {
    public int x, y;
    static float scale = 0.5f;
    int index ;
    public Eating(int x, int y, int index){
        this.x = x;
        this.y=y;
        this.index = index;
    }

    public int ConvertX() {
        return (x+1) * 15 - 2;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int ConvertY() {
        return (y+1) *15 - 8;
    }

    public static float getScale() {
        return scale;
    }

    public int getIndex() {
        return index;
    }
}
