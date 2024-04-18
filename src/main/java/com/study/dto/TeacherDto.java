package com.study.dto;

import java.util.ArrayList;
import java.util.List;

public record TeacherDto(String firstName,
                         String lastName,
                         SubjectDtoForTeacher subject) {

    public static List<Exception> validateTeacherDto(String firstName, String lastName, SubjectDtoForTeacher subjectDtoForTeacher) {

        List<Exception> errors = new ArrayList<>();

        if (firstName.isBlank()) {
            errors.add(new IllegalArgumentException("The field firstName cannot be blank"));
        }
        if (lastName.isBlank()) {
            errors.add(new IllegalArgumentException("The field lastName cannot be blank"));
        }

        validateSubject(subjectDtoForTeacher, errors);

        return errors;
    }

    private static void validateSubject(SubjectDtoForTeacher subjectDtoForTeacher, List<Exception> errors) {
        try {
            SubjectDtoForTeacher.validateSubjectDtoForTeacher(subjectDtoForTeacher.subject());
        }
        catch (IllegalArgumentException exception) {
            errors.add(exception);
        }
    }
}
