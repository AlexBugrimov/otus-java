package ru.otus.processor;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public interface TimeProvider extends Supplier<LocalDateTime> {

    LocalDateTime get();
}
