package com.study.service.handler;

import com.study.service.exception.IncorrectIdException;

public class IncorrectNameExceptionHandler implements ErrorHandler {

    private ErrorHandler nextErrorHandler;


    public IncorrectNameExceptionHandler() {
    }

    @Override
    public void handleRequest(Exception exception) {
        if (exception instanceof IncorrectIdException) {
            System.out.println("Incorrect name exception: '" + exception.getMessage() + "'");
        } else if (nextErrorHandler != null) {
            nextErrorHandler.handleRequest(exception);
        }
    }

    @Override
    public void setNextErrorHandler(ErrorHandler errorHandler) {
        this.nextErrorHandler = errorHandler;
    }
}
