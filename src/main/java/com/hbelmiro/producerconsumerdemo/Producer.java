package com.hbelmiro.producerconsumerdemo;

import java.util.Iterator;
import java.util.List;

public class Producer {

    public SlowIterator<Product> produce() {
        return new SlowIterator<>(List.of(
                new Product(1, "Product 1"),
                new Product(2, "Product 2"),
                new Product(3, "Product 3"),
                new Product(4, "Product 4"),
                new Product(5, "Product 5")
        ));
    }

}
