package com.pgdatabase.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(exclude = "mark")
public class ExamResult {

    private int studentId;
    private int subjectId;
    private int mark;

    @Override
    public String toString() {
        return String.join(",", String.valueOf(studentId), String.valueOf(subjectId), String.valueOf(mark));
    }
}
