package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.TimeSecondsException;

import java.time.LocalDateTime;

public class ProcessorWithException implements Processor {

    @Override
    public Message process(Message message) {
        final TimeProvider timeProvider = LocalDateTime::now;
        if (timeProvider.get().getSecond() % 2 == 0) {
            throw new TimeSecondsException("Четная секунда");
        }
        return message;
    }
}
