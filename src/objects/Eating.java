package objects;

public class Eating {
    public int x, y;
    static float scale = 0.5f;
    int index = 5;
    public Eating(int x,int y){
        this.x = x;
        this.y=y;
        this.index = index;
    }

    public int getX() {
        return (x+1) * 15 - 2;
    }

    public int getY() {
        return (y+1) *15 - 8;
    }

    public static float getScale() {
        return scale;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index){
        this.index = index;
    }
}
