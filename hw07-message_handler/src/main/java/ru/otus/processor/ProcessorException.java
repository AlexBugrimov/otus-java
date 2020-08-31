package ru.otus.processor;

import ru.otus.Message;

public class ProcessorException implements Processor {

    @Override
    public Message process(Message message) {
        return null;
    }
}
