/**
 * An implementation of Synchronized Singleton.
 * This definitely thread-safe and only creates one instance of Singleton
 * on concurrent environment, but unnecessarily expensive due to cost of
 * synchronization at every call.
 */
package kz.ya.dp.create.singleton;

/**
 *
 * @author yerlana
 */
public class ThreadSafeSingleton {
    
    private volatile static ThreadSafeSingleton INSTANCE;

    private ThreadSafeSingleton() {
        // preventing Singleton object instantiation from outside 
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafeSingleton();
        }
        return INSTANCE;
    }
}
