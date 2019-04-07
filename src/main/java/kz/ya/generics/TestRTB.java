package kz.ya.generics;

import java.math.BigDecimal;

/**
 * Example of Recursive Type Bound
 *
 * @author Yerlan
 */
public class TestRTB {

    public static void main(String[] args) {
        Camera nikon = new Camera(1l, new BigDecimal("1000"));
        Camera canon = new Camera(2l, new BigDecimal("1200"));

        Phone samsung = new Phone(11l, new BigDecimal("1500"));
        Phone iphone = new Phone(12l, new BigDecimal("1800"));

        if (nikon.compareTo(canon) < 0) {
            System.out.println("Nikon is chipper than Canon");
        } else {
            System.out.println("Nikon is chipper than Canon");
        }

        if (samsung.compareTo(iphone) < 0) {
            System.out.println("Samsung is chipper than Iphone");
        } else {
            System.out.println("Samsung is more expensive than Iphone");
        }

//        nikon.compareTo(samsung); // compile error
    }
}
