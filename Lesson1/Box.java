package Lesson1;

import java.util.ArrayList;

public class Box<E> {

    float sumBox;
    ArrayList <E> arrayList = new ArrayList();

    public Box() {

    }

    public void addAList(E object) {

        arrayList.add(object);

//
//        if (object instanceof Apple){
//            arrayListApple.add(object);
//            sumApple += 1;
//        }else if (object instanceof Orange){
//            arrayListOrange.add(object);
//            sumOrange += 1;
//        } else {
//            System.out.println("Объект " + object + " - нам не известен!");
//        }
//
//        System.out.println("Яблок: " + this.sumApple + ". Апельсинов: " + this.sumOrange);

    }
    public boolean compare(float sum){
        if (sumBox==sum){
            return true;
        }
        return false;
    }

//    public <E> void getWeight(float weig) {
//        if (getWeight(weig) instanceof Orange){
//
//        }
//
//        System.out.println(object.getWeig());;
//    }

//    public float getWeight(E object){
//
//
//    }
    public float getWeight(E object){
        if (object instanceof Apple){
            float sum = arrayList.size() * Apple.getWeight();
            this.sumBox = sum;
            return sum;
        }else if (object instanceof Orange){
            float sum = arrayList.size() * Orange.getWeight();
            this.sumBox = sum;
            return sum;
        } else {
            System.out.println("Объект " + object + " - нам не известен!");
            return 0;
        }
//
    }

}
