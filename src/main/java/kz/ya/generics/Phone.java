package kz.ya.generics;

import java.math.BigDecimal;

public class Phone extends Product<Phone> {

    public Phone(Long id) {
        super(id);
    }

    public Phone(Long id, BigDecimal price) {
        super(id, price);
    }
}
