package com.study.dto;

import java.util.ArrayList;
import java.util.List;

public record TeacherDto(String firstName,
                         String lastName,
                         SubjectDtoForTeacher subject) {

    public static List<ValidationResult> validateTeacherDto(String firstName, String lastName, SubjectDtoForTeacher subjectDtoForTeacher) {

        List<ValidationResult> errors = new ArrayList<>();

        if (firstName.isBlank()) {
            errors.add(ValidationResult.failed("The field firstName cannot be blank"));
        }
        if (lastName.isBlank()) {
            errors.add(ValidationResult.failed("The field lastName cannot be blank"));
        }

        validateSubject(subjectDtoForTeacher, errors);

        return errors;
    }

    private static void validateSubject(SubjectDtoForTeacher subjectDtoForTeacher, List<ValidationResult> errors) {
        ValidationResult validationResult = SubjectDtoForTeacher.validateSubjectDtoForTeacher(subjectDtoForTeacher.name());
        if (validationResult.isFailed()) errors.add(validationResult);
    }
}
