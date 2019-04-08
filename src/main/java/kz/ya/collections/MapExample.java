package kz.ya.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author yerlana
 */
public class MapExample {

    public static void main(String[] args) {
        String[] values = {"a", "b", "c", "d", "e"};
        
        // HashMap
        Map<Integer, String> m = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer key = values.length - i;
            m.put(key, values[i]);
        }
        System.out.println(m); // {1=e, 2=d, 3=c, 4=b, 5=a}

        // LinkedHashMap
        Map<Integer, String> lm = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer key = values.length - i;
            lm.put(key, values[i]);
        }
        System.out.println(lm); // {5=a, 4=b, 3=c, 2=d, 1=e}
        
        // LinkedHashMap - capacity 5, loadFactor 1 don't increase, access order
        Map<Integer, String> lm2 = new LinkedHashMap<>(5, 1, true);
        for (int i = 0; i < values.length; i++) {
            Integer key = values.length - i;
            lm2.put(key, values[i]);
        }
        System.out.println(lm2.get(3));
        System.out.println(lm2.get(5));
        System.out.println(lm2.get(1));
        System.out.println(lm2); // {4=b, 2=d, 3=c, 5=a, 1=e}
        
        // SimpleLRUCache
        Map<Integer, String> lru = new SimpleLRUCache<>(2);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        System.out.println(lru.get(2));
        lru.put(9, "i");
        System.out.println(lru); // {2=b, 9=i}
        
        // WeakHashMap
        Map<Data, String> weakMap = new WeakHashMap<>();
        Data key = new Data();
        weakMap.put(key, "information");
        
        // make available for gc
        key = null;
        System.gc();
        
        // check if data was deleted from weakMap
        for (int i = 0; i < 10000; i++) {
            if (weakMap.isEmpty()) {
                System.out.println("Empty!");
                break;
            }
        }
    }

    private static class Data {

        public Data() {
        }
    }
}
