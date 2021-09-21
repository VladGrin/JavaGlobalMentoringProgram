package com.sort;

import java.util.Objects;

public class Sorter {

    public void mergeSort(int[] items) {
        Objects.requireNonNull(items);
        if (items.length < 2) {
            return;
        }
        int mid = items.length / 2;

        int[] leftArr = new int[mid];
        System.arraycopy(items, 0, leftArr, 0, mid);

        int[] rightArr = new int[items.length - mid];
        System.arraycopy(items, mid, rightArr, 0, items.length - mid);

        mergeSort(leftArr);
        mergeSort(rightArr);

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                items[k++] = leftArr[i++];
            } else {
                items[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            items[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            items[k++] = rightArr[j++];
        }
    }

    public void insertionSort(int[] items) {
        Objects.requireNonNull(items);
        if (items.length < 2) {
            return;
        }
        for (int i = 1; i < items.length; i++) {
            int temp = items[i];
            int j = i - 1;
            while (j >= 0 && items[j] > temp) {
                items[j + 1] = items[j];
                j -= 1;
            }
            items[j + 1] = temp;
        }
    }
}
