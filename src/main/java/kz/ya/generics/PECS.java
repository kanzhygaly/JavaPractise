/**
 * Producer extends and Consumer super
 * Get And Put Principle (From Java Generics and Collections):
 * 1. use an extends wildcard when you only get values out of a structure
 * 2. use a super wildcard when you only put values into a structure
 * 3. don’t use a wildcard when you both get and put.
 */
package kz.ya.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author yerlana
 */
public class PECS {

    public static void main(String[] args) {
        // Producer extends (covariance)
        List<Integer> ints = Arrays.asList(1, 2, 3);
        System.out.println(sum(ints)); // 6.0
        List<Double> doubles = Arrays.asList(2.78, 3.14);
        System.out.println(sum(doubles)); // 5.92
        List<Number> nums = Arrays.<Number>asList(1, 2, 2.78, 3.14);
        System.out.println(sum(nums)); // 8.92

        // you cannot put anything into a type declared with an extends wildcard—except for the value null
        List<Integer> ints2 = new ArrayList<>();
        ints2.add(1);
        ints2.add(2);
        List<? extends Number> nums2 = ints2;
        nums2.add(null);  // ok
        System.out.println(nums2.toString()); // [1, 2, null]

        // Consumer super (contravariance)
        ints = new ArrayList<>();
        count(ints, 5);
        System.out.println(ints.toString()); // [0, 1, 2, 3, 4]
        nums = new ArrayList<>();
        count(nums, 5);
        nums.add(5.0);
        System.out.println(nums.toString()); // [0, 1, 2, 3, 4, 5.0]
        List<Object> objs = new ArrayList<>();
        count(objs, 5);
        objs.add("five");
        System.out.println(objs.toString()); // [0, 1, 2, 3, 4, five]

        // you cannot get anything out from a type declared with a super wildcard—except for a value of type Object
        objs = Arrays.asList(1, "two");
        List<? super Integer> ints3 = objs;
        String str = "";
        for (Object obj : ints3) {
            str += obj.toString();
        }
        System.out.println(str); // 1two
        
        // when both Get and Put, don't Use wildcard
        List<Number> nums3 = new ArrayList<>();
        System.out.println(sumCount(nums3, 7));
    }

    private static double sum(Collection<? extends Number> nums) {
        double s = 0.0;
        for (Number num : nums) {
            s += num.doubleValue();
        }
        // s = nums.stream().map((num) -> num.doubleValue()).reduce(s, (accumulator, _item) -> accumulator + _item);
        return s;
    }

    private static void count(Collection<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++) {
            ints.add(i);
        }
    }

    public static double sumCount(Collection<Number> nums, int n) {
        count(nums, n);
        return sum(nums);
    }
    
    // Collections::copy
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
      for (int i = 0; i < src.size(); i++) 
        dest.set(i, src.get(i)); 
  } 
}
