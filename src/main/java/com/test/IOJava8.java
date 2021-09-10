package com.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOJava8 {
    public static void main(String[] args) {
        System.out.println(java7Method());
        System.out.println(java8Method());
    }

    private static List<String> java8Method() {
        Path file = Paths.get("E:/JavaTutorial/JavaCore", "file.txt");
        try /*(BufferedReader in = Files.newBufferedReader(file))*/ {
            return Files.readAllLines(file);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static List<String> java7Method() {
        File file = new File("E:/JavaTutorial/JavaCore", "file.txt");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            List<String> list = new ArrayList<>();
            String s;
            while ((s = in.readLine()) != null) {
                list.add(s);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}
