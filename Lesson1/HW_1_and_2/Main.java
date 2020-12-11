package Lesson1.HW_1_and_2;

import java.util.ArrayList;

public class Main {


    private static ArrayList arrayList;

    public static void main(String[] args) {
        Integer []array = {1,2,3,4,5,6,7,8,9};
        String []array2 = {"Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять"};

        // Задание 1

        new SwapArrays(array, 2,6);
        new SwapArrays(array2, 3,7);

        for (Integer arr : array) {
            System.out.print(arr + " - ");
        }
        System.out.println();

        for (String arr : array2) {
            System.out.print(arr + " - ");
        }
        System.out.println();

        // Задание 2
        AddArList(array);
        System.out.println("Задание 2:");
        System.out.println("Из Integer в ArrayList:");

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " - ");
        }
        System.out.println();

        AddArList(array2);
        System.out.println("Из String в ArrayList:");

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " - ");
        }
        System.out.println();

    }

    static <E> void AddArList(E[] arrays){
        ArrayList arrayList = new ArrayList();
        int index = 0;
        for (E array : arrays ) {
            arrayList.add(index, array);
            index+=1;
        }
        Main.arrayList = arrayList;
    }
}
