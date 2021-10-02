package com.concurrency.task6.blocking;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingResource resource = new BlockingResource();
        Thread producer = new Thread(new BlockProducer(resource));
        Thread consumer = new Thread(new BlockConsumer(resource));
        Runtime.getRuntime().addShutdownHook(consumer);
        producer.start();
        producer.join();
    }
}
