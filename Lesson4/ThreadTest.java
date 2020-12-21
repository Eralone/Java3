package Lesson4;

import static java.lang.Thread.sleep;

public class ThreadTest { //lesson4.1
    private int status = 0;

    public synchronized void addA(){

        if (status !=0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (status !=0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.print("A");
            status++;
        notifyAll();
}

    public synchronized void addB(){

        if (status !=1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (status !=1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        status++;
        notifyAll();



    }
    public synchronized void addC(){
        if (status !=2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (status !=2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.print("C");
        status = 0;
            notifyAll();

    }

}

class addThread extends Thread{
    ThreadTest threadTest;

    public addThread(ThreadTest threadTest) {
        this.threadTest = threadTest;
    }

    @Override

    public void run() {
        for (int i = 0; i < 5; i++) {
            threadTest.addA();
        }
    }
}
class addThread1 extends Thread{
    ThreadTest threadTest;

    public addThread1(ThreadTest threadTest) {
        this.threadTest = threadTest;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            threadTest.addB();
        }
    }
}

class addThread2 extends Thread{
    ThreadTest threadTest;

    public addThread2(ThreadTest threadTest) {
        this.threadTest = threadTest;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            threadTest.addC();
        }
    }
}
class Main{
    public static void main(String[] args) {
            ThreadTest threadTest = new ThreadTest();
            new Thread(new addThread(threadTest)).start();
            new Thread(new addThread1(threadTest)).start();
            new Thread(new addThread2(threadTest)).start();

    }
}



