package ru.otus.bson.exceptions;

public class BsonException extends RuntimeException {

    public BsonException(String message) {
        super(message);
    }

    public BsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
