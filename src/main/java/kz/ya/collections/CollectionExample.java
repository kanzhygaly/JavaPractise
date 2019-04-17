package kz.ya.collections;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yerlana
 */
public class CollectionExample {

    public static void main(String[] args) {
        Map<KeyObj, Integer> map1 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            KeyObj temp = new KeyObj();
            System.out.print(temp.hashCode() + ", ");
            map1.put(temp, i);
        }
        System.out.println("");
        long startTime = System.nanoTime();
        KeyObj sKey1 = new KeyObj();
        for (int i = 0; i < 10; i++) {
            map1.containsKey(sKey1);
        }
        System.out.println("KeyObj time: " + (System.nanoTime() - startTime));
        
        Map<KeyObj0, Integer> map2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            KeyObj0 temp = new KeyObj0();
            System.out.print(temp.hashCode() + ", ");
            map2.put(temp, i);
        }
        System.out.println("");
        startTime = System.nanoTime();
        KeyObj0 sKey2 = new KeyObj0();
        for (int i = 0; i < 10; i++) {
            map2.containsKey(sKey2);
        }
        System.out.println("KeyObj0 time: " + (System.nanoTime() - startTime));
        
        Map<KeyObjHash, Integer> map3 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            KeyObjHash temp = new KeyObjHash(i);
            System.out.print(temp.hashCode() + ", ");
            map3.put(temp, i);
        }
        System.out.println("");
        startTime = System.nanoTime();
        KeyObjHash sKey3 = new KeyObjHash(10);
        for (int i = 0; i < 10; i++) {
            map3.containsKey(sKey3);
        }
        System.out.println("KeyObjHash time: " + (System.nanoTime() - startTime));
    }
    
    static class KeyObj {
    }
    
    static class KeyObj0 {

        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    static class KeyObjHash {
        
        private final int id;

        public KeyObjHash(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final KeyObjHash other = (KeyObjHash) obj;
            if (this.id != other.id) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + this.id;
            return hash;
        }
    }
}
