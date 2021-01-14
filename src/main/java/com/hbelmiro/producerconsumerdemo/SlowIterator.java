package com.hbelmiro.producerconsumerdemo;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Iterator that emulates slow iterations.
 *
 * @param <T> the type of elements returned by this iterator
 */
public class SlowIterator<T> implements Iterator<T> {

    private static final Logger LOGGER = Logger.getLogger(SlowIterator.class.getName());

    private final Iterator<T> originalIterator;

    public SlowIterator(List<T> list) {
        this.originalIterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.originalIterator.hasNext();
    }

    @Override
    public T next() {
        // Emulates heavy processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "The SlowIterator sleep was interrupted.", e);
        }

        return this.originalIterator.next();
    }

}
