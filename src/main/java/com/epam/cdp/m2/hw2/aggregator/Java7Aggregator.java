package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> map = new HashMap<>();
        for (String word : words) {
            Long amount = map.get(word);
            map.put(word, Objects.isNull(amount) ? 1L : ++amount);
        }
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
        Set<String> items = new HashSet<>();
        Set<String> resSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() - o2.length() == 0) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });
        for (String word : words) {
            if (!items.add(word.toUpperCase())) {
                resSet.add(word.toUpperCase());
            }
        }
        List<String> list = new LinkedList<>(resSet);
        return list.size() <= limit ? list : list.subList(0, (int) limit);
    }
}
