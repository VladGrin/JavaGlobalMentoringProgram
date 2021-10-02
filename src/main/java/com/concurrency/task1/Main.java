package com.concurrency.task1;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Counter counter = new Counter();
//            counter.countHashMapSum();
//            counter.countConcurrentHashMapSum();
//            counter.countSynchronizedMapSum();
            counter.countThreadSafeMapSum();
        }
    }
}
