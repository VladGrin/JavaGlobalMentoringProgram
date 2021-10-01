package com.test.algorithms;

public class RecursiveTask {
    public static void main(String[] args) {
        func(4);
        System.out.println(factorial(20));
    }

    private static void func(int x) {
        if (x == 0) {
            System.out.println("Done!");
        } else {
            System.out.println("Number " + x);
            func(x - 1);
        }
    }

    private static long factorial(int x) {
        if (x == 0) {
            return 1;
        } else {
            return x * factorial(x - 1);
        }
    }
}
