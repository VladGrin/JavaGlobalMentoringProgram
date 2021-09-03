package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Java7ParallelAggregator implements Aggregator {

    private static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinPool pool = new ForkJoinPool(NUM_OF_THREADS);
        return pool.invoke(new Sum(0, numbers.size(), numbers));
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ForkJoinPool pool = new ForkJoinPool(NUM_OF_THREADS);
        Map<String, Long> map = pool.invoke(new FrequentWord(0, words.size(), words));
        List<Map.Entry<String, Long>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        List<Pair<String, Long>> pairList = new ArrayList<>(map.size());
        int count = 0;
        for (Map.Entry<String, Long> entry : list) {
            if (count == limit) break;
            pairList.add(new Pair<>(entry.getKey(), entry.getValue()));
            count++;
        }
        return pairList;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        ForkJoinPool pool = new ForkJoinPool(NUM_OF_THREADS);
        Set<String> set = pool.invoke(new Duplicate(0, words.size(), words));
        List<String> list = new ArrayList<>(set);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() - o2.length() == 0) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });
        return list.size() <= limit ? list : list.subList(0, (int) limit);
    }

    @AllArgsConstructor
    private static class Sum extends RecursiveTask<Integer> {
        private final int low;
        private final int high;
        private final List<Integer> numbers;

        @Override
        protected Integer compute() {
            if (high - low <= 10) {
                int sum = 0;
                for (int i = low; i < high; ++i) {
                    sum += numbers.get(i);
                }
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                Sum left = new Sum(low, mid, numbers);
                Sum right = new Sum(mid, high, numbers);
                left.fork();
                int rightResult = right.compute();
                int leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

    @AllArgsConstructor
    private static class FrequentWord extends RecursiveTask<Map<String, Long>> {
        private final int low;
        private final int high;
        private final List<String> words;

        @Override
        protected Map<String, Long> compute() {
            if (high - low <= 100) {
                Map<String, Long> map = new HashMap<>();
                for (int i = low; i < high; ++i) {
                    map.put(words.get(i), frequency(words, words.get(i), low, high));
                }
                return map;
            } else {
                int mid = low + (high - low) / 2;
                FrequentWord left = new FrequentWord(low, mid, words);
                FrequentWord right = new FrequentWord(mid, high, words);
                left.fork();
                Map<String, Long> rightResult = right.compute();
                Map<String, Long> leftResult = left.join();
                return mergeAndAdd(rightResult, leftResult);
            }
        }

        @SafeVarargs
        public final Map<String, Long> mergeAndAdd(Map<String, Long>... maps) {
            Map<String, Long> result = new HashMap<>();
            for (Map<String, Long> map : maps) {
                for (Map.Entry<String, Long> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Long current = result.get(key);
                    result.put(key, current == null ? entry.getValue() : entry.getValue() + current);
                }
            }
            return result;
        }

        private long frequency(List<String> words, String searchedWord, int low, int high) {
            long count = 0;
            for (int i = low; i < high; i++) {
                if (searchedWord.equalsIgnoreCase(words.get(i))) {
                    count++;
                }
            }
            return count;
        }
    }

    @AllArgsConstructor
    private static class Duplicate extends RecursiveTask<Set<String>> {
        private final int low;
        private final int high;
        private final List<String> words;

        @Override
        protected Set<String> compute() {
            if (high - low <= 20) {
                Set<String> set = new HashSet<>();
                for (int i = low; i < high; ++i) {
                    if (frequency(words, words.get(i)) > 1) {
                        set.add(words.get(i).toUpperCase());
                    }
                }
                return set;
            } else {
                int mid = low + (high - low) / 2;
                Duplicate left = new Duplicate(low, mid, words);
                Duplicate right = new Duplicate(mid, high, words);
                left.fork();
                Set<String> rightResult = right.compute();
                Set<String> leftResult = left.join();
                leftResult.addAll(rightResult);
                return leftResult;
            }
        }

        private int frequency(List<String> words, String searchedWord) {
            int count = 0;
            for (String word : words) {
                if (searchedWord.equalsIgnoreCase(word)) {
                    count++;
                }
            }
            return count;
        }
    }
}
