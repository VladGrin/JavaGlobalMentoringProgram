package com.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.search.binary.Utils.*;
import static org.junit.Assert.assertArrayEquals;

public class SorterTest {

    private Sorter sorter;
    private int[] expected10Items;
    private int[] expected10000Items;
    private int[] shuffle10Items = new int[10];
    private int[] shuffle1000Items = new int[1_000];
    private int[] shuffle10000Items = new int[10_000];
    private int[] shuffle100000Items = new int[100_000];

    @Before
    public void init() {
        expected10Items = getItems(10);
        expected10000Items = getItems(10_000);
        shuffle10Items = shuffleArray(expected10Items);
        shuffle1000Items = shuffleArray(getItems(1_000));
        shuffle10000Items = shuffleArray(expected10000Items);
        shuffle100000Items = shuffleArray(getItems(100_000));
        sorter = new Sorter();
    }

    @Test
    public void mergeSortEmptyArray() {
        int[] expectedItems = new int[0];
        int[] items = new int[0];

        sorter.mergeSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void mergeSortOneElementArray() {
        int[] expectedItems = {10};
        int[] items = {10};

        sorter.mergeSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void mergeSortThreeElementsArray() {
        int[] expectedItems = {10, 20, 30};
        int[] items = {20, 30, 10};

        sorter.mergeSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void mergeSort10ElementsArray() {
        sorter.mergeSort(shuffle10Items);

        assertArrayEquals(expected10Items, shuffle10Items);
    }

    @Test
    public void mergeSort10000ElementsArray() {
        sorter.mergeSort(shuffle10000Items);

        assertArrayEquals(expected10000Items, shuffle10000Items);
    }

    @Test(expected = NullPointerException.class)
    public void mergeSortWhenArgumentNullThenNullPointerException() {
        sorter.mergeSort(null);
    }

    @Test
    public void insertionSortEmptyArray() {
        int[] expectedItems = new int[0];
        int[] items = new int[0];

        sorter.insertionSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void insertionSortOneElementArray() {
        int[] expectedItems = {10};
        int[] items = {10};

        sorter.insertionSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void insertionSortThreeElementsArray() {
        int[] expectedItems = {10, 20, 30};
        int[] items = {20, 30, 10};

        sorter.insertionSort(items);

        assertArrayEquals(expectedItems, items);
    }

    @Test
    public void insertionSort10ElementsArray() {
        sorter.insertionSort(shuffle10Items);

        assertArrayEquals(expected10Items, shuffle10Items);
    }

    @Test
    public void insertionSort10000ElementsArray() {
        sorter.insertionSort(shuffle10000Items);

        assertArrayEquals(expected10000Items, shuffle10000Items);
    }

    @Test(expected = NullPointerException.class)
    public void insertionSortWhenArgumentNullThenNullPointerException() {
        sorter.insertionSort(null);
    }

    @Test
    public void sort() {
        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle10Items, shuffle10Items.length)), "Merge Sort With 10 items");
        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle10Items, shuffle10Items.length)), "Insertion Sort With 10 items O(N^2)");
        benchmark(() -> sorter.mergeSort(shuffle10Items), "Insertion Sort With 10 items O(N1)");

        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle1000Items, shuffle10Items.length)), "Merge Sort With 1_000 items");
        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle1000Items, shuffle10Items.length)), "Insertion Sort With 1_000 items O(N^2)");
        benchmark(() -> sorter.mergeSort(shuffle1000Items), "Insertion Sort With 1_000 items O(N1)");

        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle10000Items, shuffle10000Items.length)), "Merge Sort With 10_000 items");
        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle10000Items, shuffle10000Items.length)), "Insertion Sort With 10_000 items O(N^2)");
        benchmark(() -> sorter.mergeSort(shuffle10000Items), "Insertion Sort With 10_000 items O(N1)");

        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle100000Items, shuffle100000Items.length)), "Merge Sort With 100_000 items");
        benchmark(() -> sorter.mergeSort(Arrays.copyOf(shuffle100000Items, shuffle100000Items.length)), "Insertion Sort With 100_000 items O(N^2)");
        benchmark(() -> sorter.mergeSort(shuffle100000Items), "Insertion Sort With 100_000 items O(N1)");
    }
}