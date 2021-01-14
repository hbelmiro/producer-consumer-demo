package com.hbelmiro.producerconsumerdemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer {

    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());

    private boolean shutdownNow = false;

    private boolean shutdownAfterQueueEmpty = false;

    public Consumer(LinkedBlockingQueue<Product> queue) {
        new Thread(() -> {
            try {
                while (!mustStopReading(queue)) {
                    Product product = queue.take();
                    writeToFile(product);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.SEVERE, "The consumer thread was interrupted.", e);
            }
        }).start();
    }

    private boolean mustStopReading(LinkedBlockingQueue<Product> queue) {
        if (this.shutdownNow) {
            return true;
        } else {
            return this.shutdownAfterQueueEmpty && queue.isEmpty();
        }
    }

    private void writeToFile(Product product) {
        // Emulates writing to file

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        LOGGER.info(product.getName());
    }

    public void shutdownAfterQueueIsEmpty() {
        this.shutdownAfterQueueEmpty = true;
    }

    public void shutdownNow() {
        this.shutdownNow = true;
    }

}
