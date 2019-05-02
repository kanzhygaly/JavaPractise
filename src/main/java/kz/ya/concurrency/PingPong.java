/*
 * Example of running threads one after another
 */
package kz.ya.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author yerlana
 */
public class PingPong {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Player("Ping"));
        executorService.submit(new Player("Pong"));
        executorService.shutdown();
    }
}

class Player implements Runnable {

    private static final int MAX = 10;
    public static Object lock = new Object();
    private final String msg;

    public Player(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int i = 0; i < MAX; i++) {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(i + " " + Thread.currentThread().getName() + ": " + msg);

                lock.notifyAll();

                // prevent thread to waid on the last iteration
                if (i < (MAX - 1)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
