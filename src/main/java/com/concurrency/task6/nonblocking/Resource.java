package com.concurrency.task6.nonblocking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Resource {
    private static final Logger LOG = LoggerFactory.getLogger(Resource.class);
    private static final Random RANDOM = new Random();

    private final Queue<Integer> numbers;

    public Resource() {
        this.numbers = new LinkedList<>();
    }

    public synchronized void generateNumbers() {
        LOG.info("Generated numbers was started.");
        for (int i = 0; i < 500; i++) {
            int number = RANDOM.nextInt(10_000);
            numbers.offer(number);
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Generated numbers was finished.");
        notify();
    }

    public synchronized void calculateTotalAverage() {
        LOG.info("Calculating was started");
        while (numbers.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int averageValue = numbers.stream().mapToInt(Integer::valueOf).sum() / numbers.size();
        LOG.info("Average value = {}", averageValue);
    }
}
