package com.study.service.handler;

import com.study.service.exception.MaxSubjectException;

public class MaxSubjectsHandler implements ErrorHandler {

    private ErrorHandler nextErrorHandler;


    public MaxSubjectsHandler() {
    }

    @Override
    public void handleRequest(Exception exception) {
        if (exception instanceof MaxSubjectException) {
            System.out.println("Max subjects exception: '" + exception.getMessage() + "'");
        } else if (nextErrorHandler != null) {
            nextErrorHandler.handleRequest(exception);
        }
    }

    @Override
    public void setNextErrorHandler(ErrorHandler errorHandler) {
        this.nextErrorHandler = errorHandler;
    }
}
