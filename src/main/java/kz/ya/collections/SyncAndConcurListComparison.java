package kz.ya.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Long> f1 = executorService.submit(new ReadList(0, 50, list, latch));
        Future<Long> f2 = executorService.submit(new ReadList(50, 100, list, latch));
        
        executorService.shutdown();

        latch.countDown();

        System.out.println(list.getClass().getSimpleName() + " execution time");
        try {
            System.out.println("Thread 1: " + (f1.get() / 1000) + " sec");
            System.out.println("Thread 2: " + (f2.get() / 1000) + " sec");
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static class ReadList implements Callable<Long> {

        private final int start;
        private final int end;
        private final List<Integer> list;
        private final CountDownLatch latch;

        public ReadList(int start, int end, List<Integer> list, CountDownLatch latch) {
            this.start = start;
            this.end = end;
            this.list = list;
            this.latch = latch;
        }

        @Override
        public Long call() throws Exception {
            latch.await();

            long startTime = System.nanoTime();

            for (int i = start; i < end; i++) {
                list.get(i);
            }

            return System.nanoTime() - startTime;
        }
    }
}
