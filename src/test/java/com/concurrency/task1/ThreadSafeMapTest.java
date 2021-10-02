package com.concurrency.task1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafeMapTest {

    private Map<Integer, Integer> map;

    @Test
    public void countThreadSafeMapSum() {
        map = new HashMap<>();
        benchmark(() -> map.put(1, 1), "CountThreadSafeMapSum Java8 put without synchronized");
        benchmark(() -> map.get(1), "CountThreadSafeMapSum Java8 get without synchronized");
        map = ThreadSafeMap.getInstance(new HashMap<>());
        benchmark(() -> map.put(1, 1), "CountThreadSafeMapSum Java8 put with synchronized");
        benchmark(() -> map.get(1), "CountThreadSafeMapSum Java8 get with synchronized");
    }

    private void benchmark(Statistic aggregator, String title) {
        int repetitions = 100;

        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            aggregator.printStats();
        }
        long end = System.nanoTime();

        double avgDuration = ((end - start) / (repetitions * 1_000_000_000d));
        System.out.println(title + " method duration = " + avgDuration);
    }

    @FunctionalInterface
    public interface Statistic {
        void printStats();
    }
}
// CountThreadSafeMapSum Java8 put without synchronized method duration = 2.78E-7
// CountThreadSafeMapSum Java8 get without synchronized method duration = 1.73E-7
// CountThreadSafeMapSum Java8 put with synchronized method duration = 4.67E-7
// CountThreadSafeMapSum Java8 get with synchronized method duration = 2.35E-7
//
// CountThreadSafeMapSum Java10 put without synchronized method duration = 2.34E-7
// CountThreadSafeMapSum Java10 get without synchronized method duration = 1.39E-7
// CountThreadSafeMapSum Java10 put with synchronized method duration = 3.35E-7
// CountThreadSafeMapSum Java10 get with synchronized method duration = 1.62E-7
//
// CountThreadSafeMapSum Java11 put without synchronized method duration = 2.27E-7
// CountThreadSafeMapSum Java11 get without synchronized method duration = 1.35E-7
// CountThreadSafeMapSum Java11 put with synchronized method duration = 3.14E-7
// CountThreadSafeMapSum Java11 get with synchronized method duration = 1.96E-7
//
