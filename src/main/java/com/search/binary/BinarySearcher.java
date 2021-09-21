package com.search.binary;

import java.util.Objects;

public class BinarySearcher {

    public int recursivelyBinarySearch(int item, int[] items) {
        Objects.requireNonNull(items);
        return recursivelyBinarySearch(item, items, 0, items.length - 1);
    }

    private int recursivelyBinarySearch(int item, int[] items, int lowerIdx, int upperIdx) {
        if (upperIdx > 0 && lowerIdx < items.length - 1) {
            int mid = lowerIdx + (upperIdx - lowerIdx) / 2;
            if (item == items[mid]) {
                return mid;
            }
            if (item > items[mid]) {
                return recursivelyBinarySearch(item, items, mid + 1, upperIdx);
            }
            return recursivelyBinarySearch(item, items, lowerIdx, mid - 1);
        }
        return -1;
    }

    public int iterativelyBinarySearch(int item, int[] items) {
        Objects.requireNonNull(items);

        int lowerIdx = 0;
        int upperIdx = items.length - 1;
        while (lowerIdx <= upperIdx) {
            int mid = lowerIdx + (upperIdx - lowerIdx) / 2;
            if (item == items[mid]) {
                return mid;
            } else if (item > items[mid]) {
                lowerIdx = mid + 1;
            } else {
                upperIdx = mid - 1;
            }
        }
        return -1;
    }
}
