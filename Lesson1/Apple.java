package Lesson1;

public class Apple extends Fruit {

    static private float weig;

    public Apple(String name) {
        super(name);
        float weig = 1.0f;
        this.weig = weig;

    }

    public static float getWeight() {
        return weig;
    }
}
