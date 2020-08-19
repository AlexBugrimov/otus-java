package ru.otus.junit.runner.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestResult {

    private final String description;
    private final State state;

    public enum State {
        PASSED, FAILED;

        @Override
        public String toString() {
            return String.format("[ %s ]", this.name().toUpperCase());
        }
    }

    @Override
    public String toString() {
        return state + ": " + description;
    }
}
