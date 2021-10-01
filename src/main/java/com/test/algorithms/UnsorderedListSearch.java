package com.test.algorithms;

public class UnsorderedListSearch {
    public static void main(String[] args) {
        int[] items = {6, 20, 88, 74, 45, 19, 67, 52, 9};
        System.out.println(findItem(45, items));
        System.out.println(findItem(100, items));
    }

    private static int findItem(int item, int[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            if (item == itemList[i]) {
                return i;
            }
        }
        return -1;
    }
}
