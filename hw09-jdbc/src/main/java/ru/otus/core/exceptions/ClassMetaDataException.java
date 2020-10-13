package ru.otus.core.exceptions;

public class ClassMetaDataException extends RuntimeException {

    public ClassMetaDataException(String message) {
        super(message);
    }

    public ClassMetaDataException(Throwable cause) {
        super(cause);
    }
}
