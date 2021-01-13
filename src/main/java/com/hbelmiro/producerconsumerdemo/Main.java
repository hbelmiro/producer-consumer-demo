package com.hbelmiro.producerconsumerdemo;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

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
        }
    }

}
