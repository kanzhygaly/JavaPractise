package kz.ya.concurrency.bank;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yerlana
 */
public class OperationsReentrant {

    private static final int LOCK_WAIT_SEC = 5;
    private static final int MAX_TRANSFER_SEC = 7;
    private static final Random waitRandom = new Random();

    public static void main(String[] args) {
        final Account a = new Account(1l, new BigDecimal(1000));
        final Account b = new Account(2l, new BigDecimal(2000));

        Thread t1 = new Thread(() -> transfer(a, b, new BigDecimal(500)));
        Thread t2 = new Thread(() -> transfer(b, a, new BigDecimal(300)));

        // print amounts
        outputAmount(a);
        outputAmount(b);

        System.out.println("OperationsReentrant...");

        // start threads
        t1.start();
        t2.start();

        try {
            // wait until threads terminate
            t1.join();
            t2.join();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        // print amounts
        outputAmount(a);
        outputAmount(b);
    }

    private static void outputAmount(Account acc) {
        System.out.println("Account " + acc.getId() + " balance: " + acc.getBalance());
    }

    private static void transfer(Account from, Account to, BigDecimal amount) {
        try {
            if (from.getLock().tryLock(LOCK_WAIT_SEC, TimeUnit.SECONDS)) {
                System.out.println("Locked " + from.getId());
                try {
                    if (to.getLock().tryLock(LOCK_WAIT_SEC, TimeUnit.SECONDS)) {
                        System.out.println("Locked " + to.getId());
                        try {
                            
                            from.withdraw(amount);
                            to.deposit(amount);
                            
                            Thread.sleep(waitRandom.nextInt(MAX_TRANSFER_SEC * 1000));
                            
                            System.out.println("Transfer " + amount + " done from " + from.getId() + " to " + to.getId());
                        } catch (InsufficientFundsException ex) {
                            from.incrFailCount();
                            System.err.println(ex);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } finally {
                            to.getLock().unlock();
                        }
                    } else {
                        to.incrFailCount();
                    }
                } finally {
                    from.getLock().unlock();
                }
            } else {
                from.incrFailCount();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
