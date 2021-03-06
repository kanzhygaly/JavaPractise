/**
 * Producer Consumer Problem solution using CustomThreadPoolExecutor and BlockingQueue in Java.
 * BlockingQueue not only provide a data structure to store data,
 * but also gives you flow control, require for inter thread communication.
 * Semaphore enables us to use shared lock between threads.
 */
package kz.ya.concurrency.prodcons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yerlana
 */
public class ProduceConsumerProblem {

    public static void main(String[] args) {        
        // create bounded BlockingQueue
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);

        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);

        executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exec) -> {
            
            System.out.println("DemoTask Rejected : " + ((DemoTask) r).getName());
            System.out.println("Waiting for a second !!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lets add another time : " + ((DemoTask) r).getName());
            exec.execute(r);
        });
        
        // Let start all core threads initially
        executor.prestartAllCoreThreads();
        
        for (int i = 1; i < 101; i++) {
            // Adding threads one by one
            System.out.println("Adding DemoTask : " + i);
            executor.execute(new DemoTask(String.valueOf(i)));
        }
        
        executor.shutdown();
    }
}
