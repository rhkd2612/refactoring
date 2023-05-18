package com.study.refactor.handler;

import java.lang.Thread.UncaughtExceptionHandler;
import com.study.refactor.exception.MyCustomException;

public class MyCustomExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(e instanceof MyCustomException){
            System.out.println("[MyCustomException] - " + e.getMessage());
            return;
        }

        System.out.println("예기치 못한 예외 발생! - " + e);
    }
}
