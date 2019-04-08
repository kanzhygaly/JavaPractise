/**
 * Producer consumer problem using wait() and notify() methods.
 * To keep program simple and to keep focus on usage of wait() and notify()
 * methods, we will involve only one producer and one consumer thread.
 */
package kz.ya.concurrency.prodcons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yerlana
 */
public class ProducerConsumerWN {

    public static void main(String[] args) {
        int MAX_CAPACITY = 5;
        List<Integer> taskQueue = new ArrayList<>(MAX_CAPACITY);
        
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "Consumer");
        
        tProducer.start();
        tConsumer.start();
    }
}
