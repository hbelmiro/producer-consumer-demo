package com.hbelmiro.producerconsumerdemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LinkedBlockingQueue<Product> queue = new LinkedBlockingQueue<>();

        Producer producer = new Producer();

        SlowIterator<Product> slowIterator = producer.produce();

        Consumer consumer = new Consumer(queue);

        try {
            while (slowIterator.hasNext()) {
                Product product = slowIterator.next();
                queue.put(product);
            }
            consumer.shutdownAfterQueueIsEmpty();
        } catch (Exception e) {
            consumer.shutdownNow();
            LOGGER.log(Level.SEVERE, "Unexpected error. The consumer will be shutdown and no more products will be read from queue", e);
        }
    }

}
