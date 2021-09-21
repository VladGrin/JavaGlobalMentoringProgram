package com.search.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static int[] getItems(int amount) {
        return Stream.iterate(1, num -> num + 2)
                .limit(amount)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static int[] shuffleArray(int[] items) {
        List<Integer> list = Arrays.stream(items)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void benchmark(Statistic aggregator, String title) {
        int repetitions = 1000;

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
