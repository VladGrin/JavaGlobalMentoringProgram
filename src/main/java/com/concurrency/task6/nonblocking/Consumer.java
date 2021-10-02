package com.concurrency.task6.nonblocking;

public class Consumer implements Runnable {

    private final Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.calculateTotalAverage();
    }
}
