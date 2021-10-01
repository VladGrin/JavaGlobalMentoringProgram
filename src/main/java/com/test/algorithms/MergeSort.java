package com.test.algorithms;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {10, 2, 9, 1, 5, 9, 7, 8, 2, 3, 4, 7, 5};
        int[] arr = new Random()
                .ints(100_000_000, 1, 100000)
                .boxed()
                .mapToInt(Integer::intValue)
                .toArray();
        final Instant start = Instant.now();
        mergeSort(arr);
        final Instant end = Instant.now();
        System.out.println(Duration.between(end, start));
    }

    private static void mergeSort(int[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            int[] leftArr = new int[mid];
            System.arraycopy(array, 0, leftArr, 0, mid);
            int[] rightArr = new int[array.length - mid];
            System.arraycopy(array, mid, rightArr, 0, array.length - mid);
            mergeSort(leftArr);
            mergeSort(rightArr);
            int i = 0, j = 0, k = 0;
            while (i < leftArr.length && j < rightArr.length) {
                if (leftArr[i] < rightArr[j]) {
                    array[k++] = leftArr[i++];
                } else {
                    array[k++] = rightArr[j++];
                }
            }
            while (i < leftArr.length) {
                array[k++] = leftArr[i++];
            }
            while (j < rightArr.length) {
                array[k++] = rightArr[j++];
            }
        }
    }
}
