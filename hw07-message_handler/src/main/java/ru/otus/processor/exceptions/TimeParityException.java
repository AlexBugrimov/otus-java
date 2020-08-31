package ru.otus.processor.exceptions;

public class TimeParityException extends RuntimeException {

    public TimeParityException(String message) {
        super(message);
    }

    public TimeParityException(Throwable throwable) {
        super(throwable);
    }
}