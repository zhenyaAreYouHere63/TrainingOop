package com.study.dto;

public record SubjectDtoForTeacher(
        String name
) {

    public static void validateSubjectDtoForTeacher(String subject) {
        if (subject.isBlank()) {
            throw new IllegalArgumentException("The field subject cannot be blank");
        }
    }
}
