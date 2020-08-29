package ru.otus.processor;

import ru.otus.Message;

public class ProcessorSwapFields implements Processor {

    @Override
    public Message process(Message message) {
        final String field11 = message.getField11();
        final String field13 = message.getField13();
        return message.toBuilder()
                .field13(field11)
                .field11(field13).build();
    }
}
