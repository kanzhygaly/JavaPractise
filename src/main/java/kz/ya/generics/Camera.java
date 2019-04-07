package kz.ya.generics;

import java.math.BigDecimal;

public class Camera extends Product<Camera> {

    public Camera(Long id) {
        super(id);
    }

    public Camera(Long id, BigDecimal price) {
        super(id, price);
    }
}
