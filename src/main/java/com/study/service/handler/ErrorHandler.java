package com.study.service.handler;


public interface ErrorHandler {
    void handleRequest(Exception exception);

    void setNextErrorHandler(ErrorHandler errorHandler);
}
