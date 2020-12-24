package Lesson4;

import java.io.IOException;

public class ThreadLesson3 extends Thread {
    float status = 1.0f;

    public synchronized void addA(int i){
        float b =(float)i+0.5f;

        if  (status !=(float) i) {
            if (status!= b) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Отсканированна " + i + " страница");
        status = status + 0.5f;
        notify();
    }

    public synchronized void addB(int i){
        float b =(float)i+0.5f;

        if (status !=(float) i) {
            if (status != b) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Отпечатанна " + i + " страница");
        status = status + 0.5f;
        notify();
    }


}

class addThreadLs3{
    ThreadLesson3 threadLesson3;
    public int i;

    public addThreadLs3(ThreadLesson3 threadLesson3) {
        this.threadLesson3 = threadLesson3;
        startThread();
    }

    private void startThread() {

            new Thread(()-> {
                for (int j = 1; j <= 10; j++) {
                    threadLesson3.addA(j);
                }
            }).start();
            new Thread(()-> {
                for (int j = 1; j <= 10; j++) {
                    threadLesson3.addB(j);
                }
            }).start();
    }
}

class MainLs3{
    public static void main(String[] args) {
        ThreadLesson3 threadLesson3 = new ThreadLesson3();
        new addThreadLs3(threadLesson3);
    }
}

