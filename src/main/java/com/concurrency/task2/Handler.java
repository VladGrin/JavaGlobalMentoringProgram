package com.concurrency.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Handler {

    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);

    private static final Random RANDOM = new Random();
    private final List<Integer> list;

    public Handler(List<Integer> list) {
        this.list = list;
    }

    public void numberWriter() {
        while (true) {
            synchronized (list) {
                list.add(RANDOM.nextInt(1000));
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void amountPrinter() {
        synchronized (list) {
            long sum = list.stream().mapToLong(Long::valueOf).sum();
            LOG.info("Sum: {}", sum);
        }
    }

    public void computingPrinter() {
        synchronized (list) {
            double res = Math.sqrt(list.stream().mapToLong(Long::valueOf).map(x -> x * x).sum());
            LOG.info("Result: {}", res);
        }
    }
}