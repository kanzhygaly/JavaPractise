/**
 * Producer Consumer Problem solution using BlockingQueue in Java.
 * BlockingQueue not only provide a data structure to store data,
 * but also gives you flow control, require for inter thread communication.
 */
package kz.ya.concurrency.prodcons;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author yerlana
 */
public class ProducerConsumerBQ {

    public static void main(String[] args) {
        // create unbounded BlockingQueue
        BlockingQueue<Integer> taskQueue = new LinkedBlockingQueue<>();

        ProducerBQ tProducer = new ProducerBQ(taskQueue);
        ConsumerBQ tConsumer = new ConsumerBQ(taskQueue);

        tProducer.start();
        tConsumer.start();
    }
}

class ProducerBQ extends Thread {

    private final BlockingQueue<Integer> taskQueue;

    public ProducerBQ(BlockingQueue<Integer> sharedQueue) {
        super("PRODUCER");
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        // no synchronization needed 
        for (int i = 0; i < 10; i++) {
            try {
                // produce a new resource in every 200 milliseconds
                Thread.sleep(200);
                taskQueue.put(i);
                System.out.println(getName() + " produced " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerBQ extends Thread {

    private final BlockingQueue<Integer> taskQueue;

    public ConsumerBQ(BlockingQueue<Integer> sharedQueue) {
        super("CONSUMER");
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer item = taskQueue.take();
                System.out.println(getName() + " consumed " + item);
                // take 500 milliseconds to process consumed resource
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
