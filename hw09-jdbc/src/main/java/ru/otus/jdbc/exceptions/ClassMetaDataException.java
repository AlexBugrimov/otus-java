package ru.otus.jdbc.exceptions;

public class ClassMetaDataException extends JdbcException {

    public ClassMetaDataException(String message) {
        super(message);
    }

    public ClassMetaDataException(Throwable cause) {
        super(cause);
    }
}
