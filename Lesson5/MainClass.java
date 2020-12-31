package Lesson5;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 10;
    public static boolean startRace = true;
    public static boolean closeRace = true;
    public static boolean WINNER = true;
    public static boolean secondpl  = true;
    public static long secondplTime;
    public static String secondplName;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        syncCar();
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }

    static void startRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }

    static void closeRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    private static void syncCar() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        Car.cyclicBarrier = cyclicBarrier;
        Semaphore semaphore = new Semaphore((int)CARS_COUNT/2);
        Tunnel.semaphore = semaphore;

    }
}

