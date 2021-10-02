package com.concurrency.task6.blocking;

public class BlockConsumer implements Runnable {

    BlockingResource resource;

    public BlockConsumer(BlockingResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.calculateTotalAverage();
    }
}
