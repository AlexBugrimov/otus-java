package ru.otus.core.exceptions;

public class JdbcException extends RuntimeException {

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
