package ru.otus.modules.controller.commands;

public class Result {

    private final boolean isExecuted;
    private final String message;

    public Result(boolean isExecuted, String message) {
        this.isExecuted = isExecuted;
        this.message = message;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public String getMessage() {
        return message;
    }
}
