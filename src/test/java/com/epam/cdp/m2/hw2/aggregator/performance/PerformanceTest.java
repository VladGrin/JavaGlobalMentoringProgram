package com.epam.cdp.m2.hw2.aggregator.performance;

import com.epam.cdp.m2.hw2.aggregator.*;
import javafx.util.Pair;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
        Instant start = Instant.now();
        int sum = java7Aggregator.sum(numbers);
        Instant end = Instant.now();
        System.out.println("Java7Aggregator sum method duration =         " + Duration.between(start, end) + ", Sum = " + sum);

        start = Instant.now();
        sum = java7ParallelAggregator.sum(numbers);
        end = Instant.now();
        System.out.println("Java7ParallelAggregator sum method duration = " + Duration.between(start, end) + ", Sum = " + sum);

        start = Instant.now();
        sum = java8Aggregator.sum(numbers);
        end = Instant.now();
        System.out.println("Java8Aggregator sum method duration =         " + Duration.between(start, end) + ", Sum = " + sum);

        start = Instant.now();
        sum = java8ParallelAggregator.sum(numbers);
        end = Instant.now();
        System.out.println("Java8ParallelAggregator sum method duration = " + Duration.between(start, end) + ", Sum = " + sum);
    }

    @Test
    public void countPerformanceMostFrequentWordsMethod() {
        Instant start = Instant.now();
        List<Pair<String, Long>> mostFrequentWords = java7Aggregator.getMostFrequentWords(words, 4);
        Instant end = Instant.now();
        System.out.println("Java7Aggregator mostFrequentWords method duration =         " + Duration.between(start, end) + ", MostFrequentWords = " + mostFrequentWords);

        start = Instant.now();
        mostFrequentWords = java7ParallelAggregator.getMostFrequentWords(words, 4);
        end = Instant.now();
        System.out.println("Java7ParallelAggregator mostFrequentWords method duration = " + Duration.between(start, end) + ", MostFrequentWords = " + mostFrequentWords);

        start = Instant.now();
        mostFrequentWords = java8Aggregator.getMostFrequentWords(words, 4);
        end = Instant.now();
        System.out.println("Java8Aggregator mostFrequentWords method duration =         " + Duration.between(start, end) + ", MostFrequentWords = " + mostFrequentWords);

        start = Instant.now();
        mostFrequentWords = java8ParallelAggregator.getMostFrequentWords(words, 4);
        end = Instant.now();
        System.out.println("Java8ParallelAggregator mostFrequentWords method duration = " + Duration.between(start, end) + ", MostFrequentWords = " + mostFrequentWords);
    }

    @Test
    public void countPerformanceDuplicatesMethod() {
        Instant start = Instant.now();
        List<String> duplicates = java7Aggregator.getDuplicates(words, 4);
        Instant end = Instant.now();
        System.out.println("Java7Aggregator getDuplicates method duration =         " + Duration.between(start, end) + ", Duplicates = " + duplicates);

        start = Instant.now();
        duplicates = java7ParallelAggregator.getDuplicates(words, 4);
        end = Instant.now();
        System.out.println("Java7ParallelAggregator getDuplicates method duration = " + Duration.between(start, end) + ", Duplicates = " + duplicates);

        start = Instant.now();
        duplicates = java8Aggregator.getDuplicates(words, 4);
        end = Instant.now();
        System.out.println("Java8Aggregator getDuplicates method duration =         " + Duration.between(start, end) + ", Duplicates = " + duplicates);

        start = Instant.now();
        duplicates = java8ParallelAggregator.getDuplicates(words, 4);
        end = Instant.now();
        System.out.println("Java8ParallelAggregator getDuplicates method duration = " + Duration.between(start, end) + ", Duplicates = " + duplicates);
    }
}
