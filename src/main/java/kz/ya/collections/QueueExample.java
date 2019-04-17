package kz.ya.collections;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author yerlana
 */
public class QueueExample {
    
    public static void main(String[] args) {
        System.out.println("Linked Queue: ");
        Queue<Integer> lQueue = new LinkedList<>();
        for (int i = 5; i > 0; i--) {
            lQueue.add(i);
        }
        while (!lQueue.isEmpty()) {
            System.out.print(lQueue.poll() + " ");
        }
        System.out.println("");
        
        System.out.println("Natural Priority Queue: ");
        Queue<Integer> pQueue = new PriorityQueue<>();
        for (int i = 5; i > 0; i--) {
            pQueue.add(i);
        }
        while (!pQueue.isEmpty()) {
            System.out.print(pQueue.poll() + " ");
        }
        System.out.println("");
        
        System.out.println("Compare Priority Queue: ");
        Queue<Integer> pcQueue = new PriorityQueue<>((Integer o1, Integer o2) -> {
            // Compare numbers to return EVEN first and then ODD
            if (o1%2 == 0 && o2%2 != 0) {
                // if o1 is EVEN and o2 is ODD, then return o1 < o2
                return -1;
            } else if (o1%2 != 0 && o2%2 == 0) {
                // if o1 is ODD and o2 is EVEN, then return o1 > o2
                return 1;
            }
            /**
             * o1%2 == 0 && o2%2 == 0
             * o1%2 != 0 && o2%2 != 0
             * If numbers are of the same type, then just compare
             */
            return o1.compareTo(o2);
        });
        for (int i = 5; i > 0; i--) {
            pcQueue.add(i);
        }
        while (!pcQueue.isEmpty()) {
            System.out.print(pcQueue.poll() + " ");
        }
    }
}
