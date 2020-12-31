package Lesson4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ThreadLesson2 extends Thread {

    private int status = 0;
    File file = new File("src/main/java/Lesson4/les2");

    public static void main(String[] args) {
        ThreadLesson2 threadLesson2 = new ThreadLesson2();
            new ObjForWr(threadLesson2);
    }

    public synchronized void wr1() throws IOException, InterruptedException {


        for (int i = 0; i < 10; i++) {
            FileWriter writer = new FileWriter(file, true);
            if (status != 0) {
                wait();
            }
            if (status != 0) {
                wait();
            }
            sleep(20);
            writer.write("Hello от потока 1 \n");
            status++;
            writer.close();
            notifyAll();
        }

    }

    public synchronized void wr2() throws IOException, InterruptedException {


        for (int i = 0; i < 10; i++) {
            FileWriter writer = new FileWriter(file, true);
            if (status != 1) {
                wait();
            }
            if (status != 1) {
                wait();
            }
            sleep(20);
            writer.write("Hello от потока 2 \n");
            status++;
            writer.close();
            notifyAll();

        }


    }

    public synchronized void wr3() throws IOException, InterruptedException {


        for (int i = 0; i < 10; i++) {
            FileWriter writer = new FileWriter(file, true);
            if (status != 2) {
                wait();
            }
            if (status != 2) {
                wait();
            }
            sleep(20);
            writer.write("Hello от потока 3 \n");
            status++;
            writer.close();
            notifyAll();
        }

    }
}
class ObjForWr {
    ThreadLesson2 threadLesson2;
    public ObjForWr(ThreadLesson2 threadLesson2) {
        this.threadLesson2 = threadLesson2;
        startThread();
    }

    private void startThread() {
        new Thread(()-> {
            try {
                threadLesson2.wr1();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                threadLesson2.wr2();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                threadLesson2.wr3();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        }
    }




