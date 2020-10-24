package ru.otus.processor.exceptions;

public class TimeSecondsException extends RuntimeException {

    public TimeSecondsException(String message) {
        super(message);
    }

    public TimeSecondsException(Throwable throwable) {
        super(throwable);
    }
}