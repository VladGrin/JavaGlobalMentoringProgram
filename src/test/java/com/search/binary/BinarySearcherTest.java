package com.search.binary;

import org.junit.Before;
import org.junit.Test;

import static com.search.binary.Utils.benchmark;
import static com.search.binary.Utils.getItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinarySearcherTest {

    private BinarySearcher searcher;

    @Before
    public void init() {
        searcher = new BinarySearcher();
    }

    @Test
    public void recursivelyBinarySearchInMiddle() {
        int pos = searcher.recursivelyBinarySearch(501, getItems(1000));

        assertThat(pos, is(250));
    }

    @Test
    public void recursivelyBinarySearchInStart() {
        int pos = searcher.recursivelyBinarySearch(3, getItems(1000));

        assertThat(pos, is(1));
    }

    @Test
    public void recursivelyBinarySearchInEnd() {
        int pos = searcher.recursivelyBinarySearch(999, getItems(1000));

        assertThat(pos, is(499));
    }

    @Test
    public void recursivelyBinarySearchWhenNumberMissing() {
        int pos = searcher.recursivelyBinarySearch(2, getItems(1000));

        assertThat(pos, is(-1));
    }

    @Test(expected = NullPointerException.class)
    public void recursivelyBinarySearchWhenItemsNullThenNullPointerException() {
        searcher.recursivelyBinarySearch(2, null);
    }

    @Test
    public void iterativelyBinarySearchInMiddle() {
        int pos = searcher.iterativelyBinarySearch(501, getItems(1000));

        assertThat(pos, is(250));
    }

    @Test
    public void iterativelyBinarySearchInStart() {
        int pos = searcher.recursivelyBinarySearch(3, getItems(1000));

        assertThat(pos, is(1));
    }

    @Test
    public void iterativelyBinarySearchInEnd() {
        int pos = searcher.recursivelyBinarySearch(999, getItems(1000));

        assertThat(pos, is(499));
    }

    @Test
    public void iterativelyBinarySearchWhenNumberMissing() {
        int pos = searcher.recursivelyBinarySearch(2, getItems(1000));

        assertThat(pos, is(-1));
    }

    @Test(expected = NullPointerException.class)
    public void iterativelyBinarySearchWhenItemsNullThenNullPointerException() {
        searcher.iterativelyBinarySearch(2, null);
    }

    @Test
    public void binarySearchInMiddle() {
        benchmark(() -> searcher.recursivelyBinarySearch(101, getItems(100)),
                "RecursivelyBinarySearch among 100 numbers in the middle");
        benchmark(() -> searcher.iterativelyBinarySearch(101, getItems(100)),
                "IterativelyBinarySearch among 100 numbers in the middle");
        benchmark(() -> searcher.recursivelyBinarySearch(1001, getItems(1000)),
                "RecursivelyBinarySearch among 1000 numbers in the middle");
        benchmark(() -> searcher.iterativelyBinarySearch(1001, getItems(1000)),
                "IterativelyBinarySearch among 1000 numbers in the middle");
        benchmark(() -> searcher.recursivelyBinarySearch(10001, getItems(10000)),
                "RecursivelyBinarySearch among 10000 numbers in the middle");
        benchmark(() -> searcher.iterativelyBinarySearch(10001, getItems(10000)),
                "IterativelyBinarySearch among 10000 numbers in the middle");
        benchmark(() -> searcher.recursivelyBinarySearch(100001, getItems(100000)),
                "RecursivelyBinarySearch among 100000 numbers in the middle");
        benchmark(() -> searcher.iterativelyBinarySearch(100001, getItems(100000)),
                "IterativelyBinarySearch among 100000 numbers in the middle");
    }

    @Test
    public void binarySearchInStart() {
        benchmark(() -> searcher.recursivelyBinarySearch(3, getItems(100)),
                "RecursivelyBinarySearch among 100 numbers in the start");
        benchmark(() -> searcher.iterativelyBinarySearch(3, getItems(100)),
                "IterativelyBinarySearch among 100 numbers in the start");
        benchmark(() -> searcher.recursivelyBinarySearch(3, getItems(1000)),
                "RecursivelyBinarySearch among 1000 numbers in the start");
        benchmark(() -> searcher.iterativelyBinarySearch(3, getItems(1000)),
                "IterativelyBinarySearch among 1000 numbers in the start");
        benchmark(() -> searcher.recursivelyBinarySearch(3, getItems(10000)),
                "RecursivelyBinarySearch among 10000 numbers in the start");
        benchmark(() -> searcher.iterativelyBinarySearch(3, getItems(10000)),
                "IterativelyBinarySearch among 10000 numbers in the start");
        benchmark(() -> searcher.recursivelyBinarySearch(3, getItems(100000)),
                "RecursivelyBinarySearch among 100000 numbers in the start");
        benchmark(() -> searcher.iterativelyBinarySearch(3, getItems(100000)),
                "IterativelyBinarySearch among 100000 numbers in the start");
    }
}