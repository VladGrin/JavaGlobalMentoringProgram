package com.linux.basics.sort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SortNumbers {
    private static final Random RANDOM = new Random();
    private static final int AMOUNT_NUMBERS = 5_000_000;
    private static final int BOUNDER = 100_001;

    public static void main(String[] args) {
        int repetitions = 10;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            sort();
        }
        long end = System.nanoTime();
        double avgDuration = ((end - start) / (repetitions * 1_000_000_000d));
        System.out.println("Method duration = " + avgDuration);
    }

    private static void sort(){
        int[] numbers = new int[AMOUNT_NUMBERS];
        for (int i = 0; i < AMOUNT_NUMBERS; i++) {
            numbers[i] = RANDOM.nextInt(BOUNDER);
        }
        Arrays.sort(numbers);
        try (FileWriter fileWriter = new FileWriter("sort_numbers.txt")) {
            for (int number : numbers) {
                fileWriter.write(number + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
