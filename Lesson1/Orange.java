package Lesson1;

public class Orange extends Fruit {
    static private float weig;

    public Orange(String name) {
        super(name);
        float weig = 1.5f;
        this.weig = weig;
    }

    public static float getWeight() {
        return weig;
    }
}
