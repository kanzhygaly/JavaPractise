/**
 * An implementation of Double Checked Locking of Singleton.
 * Intention is to minimize cost of synchronization and improve performance,
 * by only locking critical section of code, the code which creates instance of Singleton class.
 * By the way this is still broken, if we don't make INSTANCE volatile, as another thread can
 * see a half initialized instance of Singleton.
 */
package kz.ya.concurrency.singleton;

/**
 *
 * @author yerlana
 */
public class DoubleCheckedLockingSingleton {

    private volatile static DoubleCheckedLockingSingleton INSTANCE;

    // to prevent creating another instance of Singleton
    private DoubleCheckedLockingSingleton() {
        // to prevent creating another instance of Singleton using Reflection
        throw new RuntimeException("Singleton already initialized");
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                //double checking Singleton instance
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return INSTANCE;
    }
        
    private Object readResolve(){
        // to prevent creating another instance of Singleton during Serialization
        return INSTANCE;
    }
}
