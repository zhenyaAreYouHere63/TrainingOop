package com.study.service.handler;

import com.study.service.exception.NotFoundException;

public class NotFoundHandler implements ErrorHandler {

    private ErrorHandler nextErrorHandler;

    public NotFoundHandler() {
    }

    @Override
    public void handleRequest(Exception exception) {
        if (exception instanceof NotFoundException) {
            System.out.println("Not found exception: '" + exception.getMessage() + "'");
        } else if (nextErrorHandler != null) {
            nextErrorHandler.handleRequest(exception);
        }
    }

    @Override
    public void setNextErrorHandler(ErrorHandler errorHandler) {
        this.nextErrorHandler = errorHandler;
    }
}
