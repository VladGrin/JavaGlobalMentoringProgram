package com.concurrency.task6.blocking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingResource {
    private static final Logger LOG = LoggerFactory.getLogger(BlockingResource.class);
    private static final Random RANDOM = new Random();

    private final Queue<Integer> numbers;

    public BlockingResource() {
        this.numbers = new LinkedBlockingQueue<>();
    }

    public void generateNumbers() {
        LOG.info("Generated numbers was started.");
        for (int i = 0; i < 5000; i++) {
            int number = RANDOM.nextInt(10_000);
            numbers.offer(number);
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Generated numbers was finished.");
    }

    public void calculateTotalAverage() {
        LOG.info("Calculating was started");
        int averageValue = numbers.stream().mapToInt(Integer::valueOf).sum() / numbers.size();
        LOG.info("Average value = {}", averageValue);
    }
}
