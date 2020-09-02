package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.TimeParityException;

import java.time.LocalDateTime;

public class ProcessorWithException implements Processor {

    @Override
    public Message process(Message message) {
        final DateTime dateTime = () -> LocalDateTime.now().getSecond();
        if (dateTime.getSeconds() % 2 == 0) {
            throw new TimeParityException("Четная секунда");
        }
        return message;
    }
}
