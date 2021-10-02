package com.concurrency.task6.nonblocking;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        Thread producer = new Thread(new Producer(resource));
        Thread consumer = new Thread(new Consumer(resource));
        Runtime.getRuntime().addShutdownHook(consumer);
        producer.start();
        producer.join();
    }
}
