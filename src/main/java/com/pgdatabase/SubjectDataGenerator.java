package com.pgdatabase;

import com.pgdatabase.model.Subject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubjectDataGenerator {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<String> subjects = new ArrayList<>();
        Path path = Paths.get("src/main/resources/source/sub.csv");
        try {
            subjects = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/scv/subjects.csv")))) {
            writer.write("subject_id,subject_name,tutor");
            writer.newLine();
            writer.flush();
            for (int i = 0; i < 1000; i++) {
                String subjectName = subjects.get(RANDOM.nextInt(subjects.size()));

                Subject subject = Subject.builder()
                        .subjectId(i + 1)
                        .subjectName(subjectName)
                        .tutor("Tutor" + RANDOM.nextInt(1000))
                        .build();
                writer.write(subject.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
