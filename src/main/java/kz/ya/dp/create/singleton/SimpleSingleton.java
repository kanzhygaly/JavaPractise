/**
 * An implementation of Simple Singleton.
 * Creates multiple instance if two thread access getInstance() method simultaneously
 */
package kz.ya.dp.create.singleton;

/**
 *
 * @author yerlana
 */
public class SimpleSingleton {

    private volatile static SimpleSingleton INSTANCE;

    private SimpleSingleton() {
        // preventing Singleton object instantiation from outside 
    }

    public static SimpleSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleSingleton();
        }
        return INSTANCE;
    }
}
