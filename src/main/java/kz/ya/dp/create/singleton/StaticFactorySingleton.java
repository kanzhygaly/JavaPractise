/*
 * Singleton pattern example with static factory method
 */
package kz.ya.dp.create.singleton;

/**
 *
 * @author yerlana
 */
public class StaticFactorySingleton {

    // initailzed during class loading
    private static final StaticFactorySingleton INSTANCE = new StaticFactorySingleton();

    // to prevent creating another instance of Singleton
    private StaticFactorySingleton() {
        // prevent for creating another instance of Singleton using reflection
        throw new RuntimeException("Singleton already initialized");
    }

    public static StaticFactorySingleton getInstance() {
        return INSTANCE;
    }
    
    /**
     * Another problem with conventional Singletons are that once you implement
     * serializable interface they are no longer remain Singleton because readObject()
     * method always return a new instance just like constructor in Java.
     * 
     * @return 
     */
    private Object readResolve(){
        return INSTANCE;
    }
}
