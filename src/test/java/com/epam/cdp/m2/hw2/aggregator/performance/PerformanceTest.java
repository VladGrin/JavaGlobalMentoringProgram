package com.epam.cdp.m2.hw2.aggregator.performance;

import com.epam.cdp.m2.hw2.aggregator.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PerformanceTest {

    private final Aggregator java7Aggregator = new Java7Aggregator();
    private final Aggregator java7ParallelAggregator = new Java7ParallelAggregator();
    private final Aggregator java8Aggregator = new Java8Aggregator();
    private final Aggregator java8ParallelAggregator = new Java8ParallelAggregator();
    private final List<Integer> numbers = new Random()
            .ints(100_000_000, 1, 99)
            .boxed()
            .collect(Collectors.toList());
    private static final List<String> words;

    static {
        List<String> strings;
        try {
            strings = Files.readAllLines(Paths.get("words.txt"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        words = strings.stream()
                .map(str -> str.replaceAll("[.,]", ""))
                .map(String::toLowerCase)
                .map(str -> str.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Test
    public void countPerformanceSumMethod() {
        benchmark(() -> java7Aggregator.sum(numbers), "Java7Aggregator");
        benchmark(() -> java7ParallelAggregator.sum(numbers), "Java7ParallelAggregator");
        benchmark(() -> java8Aggregator.sum(numbers), "Java8Aggregator");
        benchmark(() -> java8ParallelAggregator.sum(numbers), "Java8ParallelAggregator");

    }

    @Test
    public void countPerformanceMostFrequentWordsMethod() {
        benchmark(() -> java7Aggregator.getMostFrequentWords(words, 4), "Java7Aggregator mostFrequentWords");
        benchmark(() -> java7ParallelAggregator.getMostFrequentWords(words, 4), "Java7ParallelAggregator mostFrequentWords");
        benchmark(() -> java8Aggregator.getMostFrequentWords(words, 4), "Java8Aggregator mostFrequentWords");
        benchmark(() -> java8ParallelAggregator.getMostFrequentWords(words, 4), "Java8ParallelAggregator mostFrequentWords");
    }

    @Test
    public void countPerformanceDuplicatesMethod() {
        benchmark(() -> java7Aggregator.getDuplicates(words, 4), "Java7Aggregator getDuplicates");
        benchmark(() -> java7ParallelAggregator.getDuplicates(words, 4), "Java7ParallelAggregator getDuplicates");
        benchmark(() -> java8Aggregator.getDuplicates(words, 4), "Java8Aggregator getDuplicates");
        benchmark(() -> java8ParallelAggregator.getDuplicates(words, 4), "Java8ParallelAggregator getDuplicates");
    }

    private <T> void benchmark(Supplier<T> aggregator, String title) {
        int repetitions = 10;

        long start = System.nanoTime();
        T result = null;
        for (int i = 0; i < repetitions; i++) {
            result = aggregator.get();
        }
        long end = System.nanoTime();

        double avgDuration = ((end - start) / (repetitions * 1_000_000_000d));
        System.out.println(title + " method duration = " + avgDuration + ", Result = " + result);
    }
}
