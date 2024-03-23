package com.study.dto;

import com.study.dao.SubjectType;

public record SubjectDtoForStudent(String name,
                                   SubjectType subjectType) {

    public static void validateSubjectDtoForStudent(String name, SubjectType subjectType) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("The field name cannot be blank");
        }
        if (subjectType == null) {
            throw new IllegalArgumentException("This field subjectType cannot be null");
        }
    }
}
