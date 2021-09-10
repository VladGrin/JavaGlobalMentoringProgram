package com.test;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaTest01 {

    public static void main(String[] args) throws InterruptedException {
        print(() -> "Vlad", name -> "Mr. " + name);
        doSomething(System.out::println, "Vlad");
        Thread thread = new Thread(() -> System.out.println("Hello Vlad"));
        thread.start();
        thread.join();
    }

    private static void print(NameSupplier supplier, Function<String, String> decorator) {
        System.out.println("Hello " + decorator.apply(supplier.get()));
    }

    private static void doSomething(Consumer<String> consumer, String name) {
        consumer.accept(name);
    }

    @FunctionalInterface
    private interface NameSupplier{
        String get();
        static void foo(String s) {
            System.out.println(s);
        }
        default void awesomePrint(String name) {
            System.out.println("*** " + name + " ***");
        }
    }
}
