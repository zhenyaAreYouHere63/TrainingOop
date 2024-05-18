package com.study.dto;

import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;

public record SubjectDtoForTeacher(
        String subject
) {

    public static void validateSubjectDtoForTeacher(String subject) {
        if (subject.isBlank()) {
            throw new IllegalArgumentException("The field subject cannot be blank");
        } if (!DataProvider.getLearningsSubject().contains(subject)) {
            throw new NotFoundException("This subject [" + subject + "] not exist");
        }
    }
}
