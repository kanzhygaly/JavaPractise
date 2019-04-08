package kz.ya.collections;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author yerlana
 */
public class TreeSetCompare {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>((Integer o1, Integer o2) -> {
            return 0;
        });
        
        set.add(1);
        set.add(2);
        set.add(1);
        
        System.out.println(set); // [1]
    }
}
