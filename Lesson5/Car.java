package Lesson5;

import java.util.TimeZone;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Car implements Runnable {
    private static int CARS_COUNT;
    public static CyclicBarrier cyclicBarrier;
    Semaphore semaphoreCar = new Semaphore(1);
    private long winTime;

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            cyclicBarrier.await();
            sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            if(MainClass.startRace) {
                MainClass.startRace();
                MainClass.startRace = false;
            }
            semaphoreCar.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
            if (MainClass.WINNER){
                MainClass.WINNER = false;
                winTime = System.currentTimeMillis();
                new Thread(()-> {
                    while (true) {
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!MainClass.closeRace) {
                            System.out.println("Победитель " + this.name + ". Поздравляю!!");
                            System.out.println("Он опередил " + MainClass.secondplName + " (занявшего 2-ое место) на " + (int)(MainClass.secondplTime-winTime) + " миллисекунд");
                            break;
                        }
                    }
                }).start();
            }else {
                if (MainClass.secondpl){
                 MainClass.secondpl = false;
                    MainClass.secondplTime = System.currentTimeMillis();
                    MainClass.secondplName = this.name;
                }
            }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        if(MainClass.closeRace) {
            MainClass.closeRace = false;
            MainClass.closeRace();
            }

    }
}
