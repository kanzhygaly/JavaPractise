package kz.ya.concurrency.bank;

import java.math.BigDecimal;

/**
 *
 * @author yerlana
 */
public class OperationsDeadlock {

    public static void main(String[] args) {
        final Account a = new Account(1l, new BigDecimal(1000));
        final Account b = new Account(2l, new BigDecimal(2000));

        Thread t1 = new Thread(() -> transfer(a, b, new BigDecimal(500)));
        Thread t2 = new Thread(() -> transfer(b, a, new BigDecimal(300)));

        // print amounts
        outputAmount(a);
        outputAmount(b);
        
        System.out.println("OperationsDeadlock...");
        
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
        synchronized (from) {
            System.out.println("Acquired " + from.getId());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (to) {
                System.out.println("Acquired " + to.getId());
                try {
                    from.withdraw(amount);
                    to.deposit(amount);
                } catch (InsufficientFundsException ex) {
                    from.incrFailCount();
                    System.err.println(ex);
                }                
            }
        }
    }
}
