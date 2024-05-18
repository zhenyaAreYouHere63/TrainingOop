package com.study.service.exception;

public class RepeatException extends RuntimeException {
    public RepeatException(String message) {
        super(message);
    }
}
