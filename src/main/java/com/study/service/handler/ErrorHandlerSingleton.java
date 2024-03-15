package com.study.service.handler;

public class ErrorHandlerSingleton {

    private static ErrorHandler errorHandlerInstance;

    private ErrorHandlerSingleton() {
    }

    public static ErrorHandler getInstance() {
        if(errorHandlerInstance == null) {
            errorHandlerInstance = buildErrorHandlerChain();
        }
        return errorHandlerInstance;
    }

    private static ErrorHandler buildErrorHandlerChain() {

        IdValidationHandler idHandler = new IdValidationHandler();
        IncorrectNameExceptionHandler nameHandler = new IncorrectNameExceptionHandler();
        MaxSubjectsHandler subjectsHandler = new MaxSubjectsHandler();
        RepeatExceptionHandler repeatHandler = new RepeatExceptionHandler();
        NotFoundHandler notFoundHandler = new NotFoundHandler();

        idHandler.setNextErrorHandler(nameHandler);
        nameHandler.setNextErrorHandler(subjectsHandler);
        subjectsHandler.setNextErrorHandler(repeatHandler);
        repeatHandler.setNextErrorHandler(notFoundHandler);

        return idHandler;
    }
}
