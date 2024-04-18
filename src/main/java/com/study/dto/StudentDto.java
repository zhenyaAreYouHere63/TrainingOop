package com.study.dto;

import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record StudentDto(
        String firstName,
        String lastName,
        String faculty,
        String specialty,
        GroupDto group,
        Set<SubjectDtoForStudent> subjects
) {

    public static List<Exception> validateStudentDto(String firstName, String lastName, String faculty,
                                                     String specialty, String group, Set<SubjectDtoForStudent> subjectDtoForStudents) {
        List<Exception> errors = new ArrayList<>();

        if (firstName.isBlank()) {
            errors.add(new IllegalArgumentException("The field firstname cannot be blank"));
        } if (lastName.isBlank()) {
            errors.add(new IllegalArgumentException("The field lastname cannot be blank"));
        } if (faculty.isBlank()) {
            errors.add(new IllegalArgumentException("The field faculty cannot be blank"));
        } if (specialty.isBlank()) {
            errors.add(new IllegalArgumentException("The field specialty cannot be blank"));
        } if (!DataProvider.getExistFaculties().contains(faculty)) {
            errors.add(new NotFoundException("This faculty " + faculty + " not exist"));
        } if (!DataProvider.getExistSpecialties().contains(specialty)) {
            errors.add(new NotFoundException("This specialty " + specialty + " not exist"));
        }

        List<Exception> validateSubjects = validateSubjects(subjectDtoForStudents);
        errors.addAll(validateSubjects);

        List<Exception> validateGroup = GroupDto.validateGroupDtoToGroup(group);
        errors.addAll(validateGroup);

        return errors;
    }

    public static List<Exception> validateSubjects(Set<SubjectDtoForStudent> subjects) {
        List<Exception> validateSubjects = new ArrayList<>();

        for (SubjectDtoForStudent subjectDtoForStudent : subjects) {
            try {
                SubjectDtoForStudent.validateSubjectDtoForStudent(subjectDtoForStudent.subject(), subjectDtoForStudent.subjectType());
            } catch (IllegalArgumentException exception) {
                validateSubjects.add(exception);
            }
        }

        return validateSubjects;
    }
}
