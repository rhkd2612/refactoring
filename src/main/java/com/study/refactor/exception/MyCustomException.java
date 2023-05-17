package com.study.refactor.exception;

public class MyCustomException extends RuntimeException{
    public MyCustomException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "[Log]" + super.getMessage();
    }
}
