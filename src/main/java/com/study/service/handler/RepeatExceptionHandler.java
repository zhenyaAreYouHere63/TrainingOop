package com.study.service.handler;

import com.study.service.exception.RepeatException;

public class RepeatExceptionHandler implements ErrorHandler {

    private ErrorHandler nextErrorHandler;


    public RepeatExceptionHandler() {
    }

    @Override
    public void handleRequest(Exception exception) {
        if(exception instanceof RepeatException) {
            System.out.println("Repeat exception: '" + exception.getMessage() + "'");
        } else if (nextErrorHandler != null) {
            nextErrorHandler.handleRequest(exception);
        }
    }

    @Override
    public void setNextErrorHandler(ErrorHandler errorHandler) {
        this.nextErrorHandler = errorHandler;
    }
}
