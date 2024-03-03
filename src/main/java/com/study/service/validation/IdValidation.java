package com.study.service.validation;

import com.study.service.exception.IncorrectIdException;

public interface IdValidation {

    default boolean idValidation(int studentId) {
        try {
            idValidatable(studentId);
            return true;
        }
        catch (IncorrectIdException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void idValidatable(int studentId) {
        if (studentId <= 0) {
            throw new IncorrectIdException("id cannot be less than 1");
        }
    }
}
