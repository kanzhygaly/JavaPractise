package kz.ya.collections;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 *
 * @author yerlana
 */
public class SimpleLRUCache<K, V> extends LinkedHashMap<K, V> {
    
    private final int capacity;

    public SimpleLRUCache(int capacity) {
        super(capacity + 1, 1.1f, true); // capacity+1 -> because removeEldestEntry is called after adding elem
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > capacity;
    }
}
