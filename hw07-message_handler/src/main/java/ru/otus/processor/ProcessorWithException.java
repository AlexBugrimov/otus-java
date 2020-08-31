package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.TimeParityException;

import java.time.LocalDateTime;

public class ProcessorWithException implements Processor {

    @Override
    public Message process(Message message) {
        final int second = LocalDateTime.now().getSecond();
        if (second % 2 == 0) {
            throw new TimeParityException("Четная секунда");
        }
        return message;
    }
}
