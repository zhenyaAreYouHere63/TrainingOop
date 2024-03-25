package com.study.dto;

public record SubjectDtoForTeacher(String name) {

    public static ValidationResult validateSubjectDtoForTeacher(String subject) {
        if (subject.isBlank()) {
           return ValidationResult.failed("The field subject cannot be blank");
        }
        return ValidationResult.succes("Validation is successful");
    }
}
