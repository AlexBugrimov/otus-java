package ru.otus.controller.commands;

public class Result {

    private final boolean isExecuted;
    private final String message;

    public Result(boolean isExecuted, String message) {
        this.isExecuted = isExecuted;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Результат { %s - %s }", isExecuted, message);
    }
}
