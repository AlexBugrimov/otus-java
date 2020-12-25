package ru.otus.appcontainer.error;

public class InstanceCreatingException extends RuntimeException {

    public InstanceCreatingException(Throwable cause) {
        super(cause);
    }
}
