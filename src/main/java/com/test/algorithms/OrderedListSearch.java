package com.test.algorithms;

public class OrderedListSearch {
    public static void main(String[] args) {
        int[] items = {2, 12, 32, 48, 59, 68, 79, 90, 96, 99};
        System.out.println(binarySearch(90, items));
        System.out.println(binarySearch(30, items));
    }

    private static int binarySearch(int item, int[] items) {
        int listSize = items.length - 1;
        int lowerIdx = 0;
        int upperIdx = listSize;
        while (lowerIdx <= upperIdx) {
            int mid = (lowerIdx + upperIdx) / 2;
            if (item == items[mid]) {
                return mid;
            } else if (item > items[mid]) {
                lowerIdx = mid + 1;
            } else if (item < items[mid]) {
                upperIdx = mid - 1;
            }
        }
        return -1;
    }
}
