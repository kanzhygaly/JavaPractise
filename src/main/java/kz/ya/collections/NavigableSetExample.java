package kz.ya.collections;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author yerlana
 */
public class NavigableSetExample {
    
    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>((Integer o1, Integer o2) -> {
            return o1.compareTo(o2);
        });
        for (int i = 0; i < 11; i++) {
            set.add(i);
        }
        System.out.println(getNextElem(set, 3));
        System.out.println(getPrevElems(set, 8));
    }
    
    private static Integer getNextElem(NavigableSet<Integer> set, Integer elem) {
        return set.higher(elem);
    }
    
    private static SortedSet<Integer> getPrevElems(NavigableSet<Integer> set, Integer elem) {
        return set.headSet(elem);
    }
}
