package com.study.dto;

public class ValidationResult {
    private String message;
    private Status status;

    public ValidationResult(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public static ValidationResult failed(String message){
        return new ValidationResult(message, Status.FAILURE);
    }

    public static ValidationResult succes(String message){
        return new ValidationResult(message, Status.SUCCESS);
    }

    boolean isFailed(){
        return status.equals(Status.FAILURE);
    }

    enum Status{
        SUCCESS, FAILURE
    }
}
