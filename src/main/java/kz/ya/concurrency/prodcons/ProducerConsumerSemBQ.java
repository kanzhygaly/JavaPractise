/**
 * Producer Consumer Problem solution using Semaphore and BlockingQueue in Java.
 * BlockingQueue not only provide a data structure to store data,
 * but also gives you flow control, require for inter thread communication.
 * Semaphore enables us to use shared lock between threads.
 */
package kz.ya.concurrency.prodcons;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author yerlana
 */
public class ProducerConsumerSemBQ {

    // limit up to 2 consumer threads processing at one time
    static private Semaphore sem = new Semaphore(2);

    public static void main(String[] args) {
        // limit capacity to 10 entries in a queue
        BlockingQueue<Integer> taskQueue = new LinkedBlockingQueue<>(10);

        // run 1 producer thread to populate the queue
        Producer tProducer = new Producer(taskQueue);
        tProducer.start();

        ExecutorService service = Executors.newCachedThreadPool();
        // run 5 consumer threads
        for (int i = 1; i < 6; i++) {
            service.execute(new Consumer(i, taskQueue));
        }        
        // wait for all consumer threads finished and then shutdown
        service.shutdown();
    }

    static class Producer extends Thread {

        private final BlockingQueue<Integer> taskQueue;

        public Producer(BlockingQueue<Integer> sharedQueue) {
            super("PRODUCER");
            this.taskQueue = sharedQueue;
        }

        @Override
        public void run() {
            // add 20 entries to the queue
            for (int i = 1; i < 21; i++) {
                try {
                    // produce a new resource in every 200 milliseconds
                    Thread.sleep(200);
                    taskQueue.put(i);
                    System.out.println(this.getName() + " produced [" + i + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {

        private final BlockingQueue<Integer> taskQueue;

        public Consumer(int id, BlockingQueue<Integer> sharedQueue) {
            super("CONSUMER " + id);
            this.taskQueue = sharedQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    sem.acquire();
                    System.out.println(this.getName() + " - aquired");
                    
                    Integer item = taskQueue.take();
                    System.out.println(this.getName() + " consumed [" + item + "]");
                    // take 500 milliseconds to process consumed resource
                    Thread.sleep(500);
                    
                    sem.release();
                    System.out.println(this.getName() + " - released");                    
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
