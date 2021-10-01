package com.test.algorithms;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {

        int[] arr = new Random()
                .ints(100_000_000, 1, 100000)
                .boxed()
                .mapToInt(x -> x)
                .toArray();
        final Instant start = Instant.now();
        quickSort(arr, 0, arr.length - 1);
        final Instant end = Instant.now();
        System.out.println(Duration.between(end, start));
    }

    private static void quickSort(int[] array, int first, int last) {
        if (first < last) {
            int pivotIdx = partition(array, first, last);
            quickSort(array, first, pivotIdx - 1);
            quickSort(array, pivotIdx + 1, last);
        }
    }

    private static int partition(int[] array, int first, int last) {
        int pivotValue = array[first];
        int lower = first + 1;
        int upper = last;
        boolean done = false;
        while (!done) {
            while (lower <= upper && array[lower] <= pivotValue) lower++;
            while (array[upper] >= pivotValue && upper >= lower) upper--;
            if (upper < lower) {
                done = true;
            } else {
                int temp = array[lower];
                array[lower] = array[upper];
                array[upper] = temp;
            }
        }
        int temp = array[first];
        array[first] = array[upper];
        array[upper] = temp;
        return upper;
    }
}
