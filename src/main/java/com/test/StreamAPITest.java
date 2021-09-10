package com.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
class Academy {
    private long id;
    private String name;
    private List<Student> students;
}

@AllArgsConstructor
@Getter
class Student {
    private int id;
    private String name;
    private List<Lesson> lessons;
}

@AllArgsConstructor
@Getter
@ToString
class Lesson {
    private int id;
    private String name;
}

public class StreamAPITest {
    public static void main(String[] args) {
        Academy academy = init();
        List<List<Lesson>> lessons = academy.getStudents().stream()
                .map(Student::getLessons)
                .collect(Collectors.toList());
        System.out.println(lessons);

        List<Lesson> lessons1 = academy.getStudents().stream()
                .flatMap(student -> student.getLessons().stream())
                .collect(Collectors.toList());
        System.out.println(lessons1);

        List<Lesson> lessons2 = academy.getStudents().stream()
                .flatMap(student -> student.getLessons().stream())
                .distinct()
                .sorted(Comparator.comparing(Lesson::getName).reversed())
                .collect(Collectors.toList());
        System.out.println(lessons2);

        Map<Integer, Lesson> lessons3 = academy.getStudents().stream()
                .flatMap(student -> student.getLessons().stream())
                .distinct()
                .collect(Collectors.toMap(Lesson::getId, Function.identity()));
        System.out.println(lessons3);
    }

    private static final Academy init() {
        Lesson math = new Lesson(10, "Math");
        Lesson programming = new Lesson(11, "Programming");
        Lesson physics = new Lesson(12, "Physics");
        Lesson literature = new Lesson(13, "Literature");
        Lesson geometry = new Lesson(14, "Geometry");
        Student max = new Student(5, "Max", Arrays.asList(math, programming));
        Student bob = new Student(6, "Bob", Arrays.asList(math, physics));
        Student nick = new Student(7, "Nick", Arrays.asList(literature, geometry));
        return new Academy(1L, "ITA", Arrays.asList(max, bob, nick));
    }
}


