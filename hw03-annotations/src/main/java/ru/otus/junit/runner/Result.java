package ru.otus.junit.runner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class Result {

    private final String testName;
    private final State state;

    enum State {
        PASSED, FAILED;

        @Override
        public String toString() {
            return String.format("[ %s ]", this.name().toUpperCase());
        }
    }
}


