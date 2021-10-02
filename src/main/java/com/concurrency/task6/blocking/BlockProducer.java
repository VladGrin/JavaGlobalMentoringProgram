package com.concurrency.task6.blocking;

public class BlockProducer implements Runnable {

    BlockingResource resource;

    public BlockProducer(BlockingResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.generateNumbers();
    }
}
