package kz.ya.concurrency.bank;

/**
 *
 * @author yerlana
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }
}
