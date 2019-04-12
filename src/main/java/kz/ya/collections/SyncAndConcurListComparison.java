package kz.ya.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yerlana
 */
public class SyncAndConcurListComparison {
    
    public static void main(String[] args) {
        List<Integer> concurrentList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            concurrentList.add(i);
        }
        compareReadTimeByTwoThreads(concurrentList);
        
        List<Integer> simpleList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            simpleList.add(i);
        }
        compareReadTimeByTwoThreads(simpleList);
    }

    private static void compareReadTimeByTwoThreads(List<Integer> list) {    
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        long startTime = System.nanoTime();

        executorService.submit(() -> readList(list));
        executorService.submit(() -> readList(list));

        executorService.shutdown();

        boolean rezWait = false;
        try {
            // Waiting for all tasks to complete
            rezWait = executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (!rezWait) {
            System.err.println("Not all tasks have completed.");
        }
        
        long time = System.nanoTime() - startTime;
        System.out.println(list.getClass().getSimpleName() + " Execution time: " + time);
    }

    private static void readList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    }
}
