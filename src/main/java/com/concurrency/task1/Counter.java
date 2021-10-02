package com.concurrency.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Counter {

    private static final Logger LOG = LoggerFactory.getLogger(Counter.class);

    private final Map<Integer, Integer> map = new HashMap<>(500);
    private final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(500);
    private final Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
    private final ThreadSafeMap<Integer, Integer> safeMap = ThreadSafeMap.getInstance(new HashMap<>());

    public void countHashMapSum() {
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                map.put(i, i);
            }
        }).start();
        new Thread(() -> {
            int sum = map.values().stream().mapToInt(Integer::valueOf).sum();
            LOG.info("Total: {}", sum);
        }).start();
    }

    public void countConcurrentHashMapSum() {
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                concurrentHashMap.put(i, i);
            }
        }).start();
        new Thread(() -> {
            int sum = concurrentHashMap.values().stream().mapToInt(Integer::valueOf).sum();
            LOG.info("Total: {}", sum);
        }).start();
    }

    public void countSynchronizedMapSum() {
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronizedMap.put(i, i);
            }
        }).start();
        new Thread(() -> {
            synchronized (synchronizedMap) {
                int sum = synchronizedMap.values().stream().mapToInt(Integer::valueOf).sum();
                LOG.info("Total: {}", sum);
            }
        }).start();
    }

    public void countThreadSafeMapSum() {
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                safeMap.put(i, i);
            }
        }).start();
        new Thread(() -> {
            int sum = safeMap.values().stream().mapToInt(Integer::valueOf).sum();
            LOG.info("Total: {}", sum);
        }).start();
    }
}