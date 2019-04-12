package kz.ya.exception;

/**
 *
 * @author yerlana
 */
public class TestTryBlock {
    
    public static void main(String[] args) {
        try {
            System.out.println("Throw exception in TRY");
            throw new RuntimeException("RuntimeException");
        } catch(RuntimeException ex) {
            System.out.println("CATCH exception: " + ex.getMessage());
            return;
        } finally {
            System.out.println("FINALLY do something");
        }
    }
}
