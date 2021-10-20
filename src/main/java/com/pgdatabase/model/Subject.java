package com.pgdatabase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subject {

    private int subjectId;
    private String subjectName;
    private String tutor;

    @Override
    public String toString() {
        return String.join(",", String.valueOf(subjectId), subjectName, tutor);
    }
}
