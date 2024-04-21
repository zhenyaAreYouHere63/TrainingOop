package com.study.dto;

import com.study.dao.store.DataProvider;
import com.study.dao.SubjectType;
import com.study.service.exception.NotFoundException;

public record SubjectDtoForStudent(String subject,
                                   SubjectType subjectType) {

    public static void validateSubjectDtoForStudent(String subject, SubjectType subjectType) {
        if (subject.isBlank()) {
            throw new IllegalArgumentException("The field subject cannot be blank");
        } if (subjectType == null) {
            throw new IllegalArgumentException("This field subjectType cannot be null");
        } if (!DataProvider.getLearningsSubject().contains(subject)) {
            throw new NotFoundException("This subject [" + subject + "] not exist");
        }
    }
}
