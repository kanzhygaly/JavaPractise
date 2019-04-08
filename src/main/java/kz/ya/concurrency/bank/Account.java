package kz.ya.concurrency.bank;

import java.math.BigDecimal;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author yerlana
 */
public class Account {

    private final Long id;
    private BigDecimal balance;
    private final LongAdder failCounter = new LongAdder();
    private final Lock lock = new ReentrantLock();

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public void deposit(final BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(final BigDecimal amount) throws InsufficientFundsException {
        if (amount.compareTo(balance) == 1) {
            throw new InsufficientFundsException("Not enough funds on Account " + id);
        }
        balance = balance.subtract(amount);
    }

    public void incrFailCount() {
        failCounter.increment();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    
    public long getFailCount() {
        return failCounter.sum();
    }

    public Lock getLock() {
        return lock;
    }
}
