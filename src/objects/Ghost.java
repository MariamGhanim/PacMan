package objects;

import java.util.Random;

public class Ghost {
    int maxWidth = 600;
    int maxHeight = 600;

    int x, y;
    int index;
    final float scale = 0.4f;
    Random random = new Random();

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

    // حركة عشوائية
    public void moveRandomly() {
        int direction = random.nextInt(4); // 0: للأعلى، 1: للأسفل، 2: لليسار، 3: لليمين

        switch (direction) {
            case 0: // للأعلى
                if (y - 15 >= 0) { // التأكد من عدم الخروج من الحد العلوي
                    y -= 15;
                }
                break;
            case 1: // للأسفل
                if (y + 15 < maxHeight ) { // التأكد من عدم الخروج من الحد السفلي
                    y += 15;
                }
                break;
            case 2: // لليسار
                if (x - 15 >= 0) { // التأكد من عدم الخروج من الحد الأيسر
                    x -= 15;
                }
                break;
            case 3: // لليمين
               if(x +15 <maxWidth) {
                   x += 15;
               }
                break;
        }
    }
}
