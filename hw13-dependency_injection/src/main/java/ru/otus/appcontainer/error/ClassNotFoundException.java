package ru.otus.appcontainer.error;

public class ClassNotFoundException extends RuntimeException {

    public ClassNotFoundException(String message) {
        super(message);
    }
}
