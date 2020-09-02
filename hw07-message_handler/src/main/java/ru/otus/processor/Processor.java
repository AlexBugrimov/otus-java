package ru.otus.processor;

import ru.otus.Message;

public interface Processor {

    Message process(Message message);

}
