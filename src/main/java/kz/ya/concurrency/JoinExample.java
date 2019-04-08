/**
 * You have thread T1, T2, and T3. How will you ensure that thread T2 is run after T1 and thread T3 after T2?
 */
package kz.ya.concurrency;

/**
 *
 * @author yerlana
 */
public class JoinExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> doWork("T1"));
        Thread t2 = new Thread(() -> doWork("T2"));
        Thread t3 = new Thread(() -> doWork("T3"));

        try {
            t1.start();
            t1.join(); // wait for t1 completion
            t2.start();
            t2.join(); // wait for t2 completion
            t3.start();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static void doWork(String threadId) {
        System.out.println(threadId + " thread started");
        try {
            // simulate work for 0.5 sec
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(threadId + " thread completed");
    }
}
