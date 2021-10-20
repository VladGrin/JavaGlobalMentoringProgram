package com.pgdatabase.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Student {

    private int studentsId;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String primarySkill;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Override
    public String toString() {
        return String.join(",", String.valueOf(studentsId), name, surname, dateOfBirth.toString(),
                phoneNumber, primarySkill, createdDatetime.toString(), updatedDatetime.toString());
    }
}
