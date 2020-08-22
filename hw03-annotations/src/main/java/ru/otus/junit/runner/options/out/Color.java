package ru.otus.junit.runner.options.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {

    YELLOW("[33m"), GREEN("[32m"), RED("[31m"), DEFAULT("[39m");

    private final String code;
}
