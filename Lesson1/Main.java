package Lesson1;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {

        Box<Orange> boxOr = new Box();

        for (int i = 1; i <= 10; i++) {
            Orange orange = new Orange("Апельсин " + i);
            boxOr.addAList(orange);
        }

        Box<Apple> boxAp = new Box();
        for (int i = 1; i <= 17; i++) {
            Apple apple = new Apple("Яблоко " + i);
            boxAp.addAList(apple);
        }

        Apple apple1 = new Apple("");
        boxAp.addAList(apple1);

        Orange orange1 = new Orange("");
        boxOr.addAList(orange1);

        Orange orange2 = new Orange("");
        boxOr.addAList(orange2);

        System.out.println("Вес коробки с яблоками: " + boxAp.getWeight(apple1));
        System.out.println("Вес коробки с апельсинами: " + boxOr.getWeight(orange1));

        System.out.println("Равны ли эти коробки? Ответ: " + boxAp.compare(boxOr.getWeight(orange1)));


        Box<Orange> boxOr1 = new Box();

        boxOr.cutBox(boxOr1);


        System.out.println("После вырезки \nБокс Апельсинов(старый) содержит:");
        for (Orange orange : boxOr.arrayList) {
            System.out.println(orange);
        }

        System.out.println("Бокс Апельсинов(новый) содержит:");
        for (Orange orange : boxOr1.arrayList) {
            System.out.println(orange);
        }




//        for (Object o : boxAp.arrayList) {
//            System.out.println(o);
//        }
//        for (Object b : boxOr.arrayList) {
//            System.out.println(b);
//        }
//
//
//
//
//
//        for (int i = 0; i < 10; i++) {
//            Apple apple = new Apple("Яблоко " +i);
//            box.addAList(apple);
//        }
//        Apple apple = new Apple("Яблоко ");
//        box.addAList(apple);
//        Orange orange = new Orange("Апельсин");
//        box.addAList(orange);
//        Orange orange1 = new Orange("Апельсин");
//        box.addAList(orange1);
//        Orange orange2 = new Orange("Апельсин");
//        box.addAList(orange2);
//
//        Object test = new Object();
//        box.addAList(test);
//
//        System.out.println(box.compare());
//
//        Box<Orange> box1 = new Box<>();
//        Apple apple3 = new Apple("Яблоко ");
//        box1.addAList(apple3);
//
////        box.getWeight((orange.getWeig());
////        box.getWeight(orange.getWeig());


    }

}
