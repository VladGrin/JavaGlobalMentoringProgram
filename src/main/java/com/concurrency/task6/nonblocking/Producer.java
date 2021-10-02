package com.concurrency.task6.nonblocking;

public class Producer implements Runnable {

    private final Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.generateNumbers();
    }
}