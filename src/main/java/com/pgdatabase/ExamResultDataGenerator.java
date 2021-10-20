package com.pgdatabase;

import com.pgdatabase.model.ExamResult;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class ExamResultDataGenerator {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/scv/exam_results.csv")))) {
            writer.write("student_id,subject_id,mark");
            writer.newLine();
            writer.flush();
            List<ExamResult> examResultsList = new ArrayList<>();
            for (int i = 0; i < 1_100_000; i++) {
                int studentId = 1 + RANDOM.nextInt(100_000);
                int subjectId = 1 + RANDOM.nextInt(1_000);
                int mark = 1 + RANDOM.nextInt(12);
                ExamResult examResult = ExamResult.builder()
                        .studentId(studentId)
                        .subjectId(subjectId)
                        .mark(mark)
                        .build();
                examResultsList.add(examResult);
            }
            new HashSet<>(examResultsList).stream()
                    .sorted(Comparator.comparing(ExamResult::getStudentId)
                            .thenComparing(ExamResult::getSubjectId).thenComparing(ExamResult::getMark))
                    .limit(1_000_000)
                    .forEach(examResult -> {
                        try {
                            writer.write(examResult.toString());
                            writer.newLine();
                            writer.flush();
                        } catch (IOException ioException) {
                            throw new RuntimeException();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

