package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                .limit(limit)
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> items = new HashSet<>();
        return words.stream()
                .map(String::toUpperCase)
                .filter(word -> !items.add(word))
                .distinct()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
