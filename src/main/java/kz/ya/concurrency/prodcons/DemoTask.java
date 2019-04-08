package kz.ya.concurrency.prodcons;

/**
 *
 * @author yerlana
 */
public class DemoTask implements Runnable {

    private final String name;

    public DemoTask(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing : " + name);
    }
}