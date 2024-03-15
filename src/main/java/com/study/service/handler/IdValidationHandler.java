package com.study.service.handler;

import com.study.service.exception.IncorrectIdException;

public class IdValidationHandler implements ErrorHandler {

    private ErrorHandler nextErrorHandler;

    public IdValidationHandler() {
    }

    @Override
    public void handleRequest(Exception exception) {
        if (exception instanceof IncorrectIdException) {
            System.out.println("Incorrect id exception: '" + exception.getMessage() + "'" );
        } else if (nextErrorHandler != null) {
            nextErrorHandler.handleRequest(exception);
        }
    }

    @Override
    public void setNextErrorHandler(ErrorHandler errorHandler) {
        this.nextErrorHandler = errorHandler;
    }
}
