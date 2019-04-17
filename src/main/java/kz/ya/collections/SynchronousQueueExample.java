package kz.ya.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 *
 * @author yerlana
 */
public class SynchronousQueueExample {

    private final static BlockingQueue<Integer> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Producer " + Thread.currentThread().getName() + " starts to work");
                    Thread.sleep(1000);
                    System.out.println("Producer " + Thread.currentThread().getName() + " puts element " + i + " into the queue");
                    queue.put(i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    int i = queue.take();
                    System.out.println("Consumer " + Thread.currentThread().getName() + " take element " + i + ", starts to work");
                    Thread.sleep(1000);
                    System.out.println("Consumer " + Thread.currentThread().getName() + " finished the work");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
