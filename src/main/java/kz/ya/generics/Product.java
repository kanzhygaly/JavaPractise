package kz.ya.generics;

import java.math.BigDecimal;

public class Product<T extends Product<T>> implements Comparable<T> {

    private final Long id;
    private BigDecimal price;

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int compareTo(T o) {
        return price.compareTo(o.getPrice());
    }
}
