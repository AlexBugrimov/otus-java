package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.TimeSecondsException;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class ProcessorWithException implements Processor {

    private final Supplier<LocalDateTime> dateTimeSupplier;

    public ProcessorWithException(Supplier<LocalDateTime> dateTimeSupplier) {
        this.dateTimeSupplier = dateTimeSupplier;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeSupplier.get().getSecond() % 2 == 0) {
            throw new TimeSecondsException("Четная секунда");
        }
        return message;
    }
}
