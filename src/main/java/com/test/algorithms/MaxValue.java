package com.test.algorithms;

public class MaxValue {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 56, 7, 9};
        int maxValue = findMaxValue(arr);
        System.out.println(maxValue);
    }

    private static int findMaxValue(int[] items) {
        if (items.length == 1) return items[0];
        int op1 = items[0];
        System.out.println("op1: " + op1);
        int[] arr = new int[items.length - 1];
        System.arraycopy(items, 1, arr, 0, items.length - 1);
        int op2 = findMaxValue(arr);
        System.out.println("op2: " + op2);
        return Math.max(op1, op2);
    }
}
