package com.test.algorithms;

public class IsSorted {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 5, 6, 7, 8, 9};
        int[] arr2 = {1, 2, 3, 5, 7, 8, 7, 9};
        System.out.println(isSorted(arr1));
        System.out.println(isSorted(arr2));
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
