package ru.otus;

public class CustomInterruptedException extends RuntimeException {

    public CustomInterruptedException(Throwable ex) {
        super(ex);
    }
}
