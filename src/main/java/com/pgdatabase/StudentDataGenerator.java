package com.pgdatabase;

import com.pgdatabase.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentDataGenerator {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(1000);
        List<String> surnames = new ArrayList<>(1000);
        readNamesAndSurnamesFromFile(names, surnames);
        List<String> primarySkills = readPrimarySkillsFromFile();
        writeStudentsToFile(names, surnames, primarySkills);
    }

    private static void writeStudentsToFile(List<String> names, List<String> surnames, List<String> primarySkills) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/scv/students.csv")))) {
            writer.write("student_id,name,surname,date_of_birth,phone_number,primary_skill,created_datetime,updated_datetime");
            writer.newLine();
            writer.flush();
            for (int i = 0; i < 100_000; i++) {
                String name = names.get(RANDOM.nextInt(names.size()));
                String surname = surnames.get(RANDOM.nextInt(names.size()));
                LocalDate dateOfBirth = LocalDate.of((1995 + RANDOM.nextInt(9)),
                        1 + RANDOM.nextInt(12), 1 + RANDOM.nextInt(28));
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 7; j++) {
                    builder.append(RANDOM.nextInt(10));
                }
                String phoneNumber = "+38-067-" + builder;
                String primarySkill = primarySkills.get(RANDOM.nextInt(primarySkills.size()));
                Student student = Student.builder()
                        .studentsId(i + 1)
                        .name(name)
                        .surname(surname)
                        .dateOfBirth(dateOfBirth)
                        .phoneNumber(phoneNumber)
                        .primarySkill(primarySkill)
                        .createdDatetime(LocalDateTime.now())
                        .updatedDatetime(LocalDateTime.now())
                        .build();
                writer.write(student.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<String> readPrimarySkillsFromFile() {
        Path path = Paths.get("src/main/resources/source/skills.csv");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void readNamesAndSurnamesFromFile(List<String> names, List<String> surnames) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/source/persons.csv")))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                names.add(split[1]);
                surnames.add(split[2]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
