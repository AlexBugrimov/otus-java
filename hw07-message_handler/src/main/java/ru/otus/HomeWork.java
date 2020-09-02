package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.Listener;
import ru.otus.listener.ListenerHistory;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorSwapFields11And13;
import ru.otus.processor.ProcessorWithException;
import ru.otus.processor.exceptions.TimeSecondsException;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13
       2. Сделать процессор, который поменяет местами значения field11 и field13
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду
       4. Сделать Listener для ведения истории: старое сообщение - новое
     */

    public static void main(String[] args) {
        List<Processor> processors = List.of(
                new ProcessorSwapFields11And13(),
                new ProcessorWithException());

        final ComplexProcessor complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TimeSecondsException(ex);
        });
        Listener listenerHistory = new ListenerHistory();
        complexProcessor.addListener(listenerHistory);

        final Message originalMessage = new Message.Builder()
                .field11("field11")
                .field12("field12")
                .field13("field13")
                .build();
        Message firstResult = complexProcessor.handle(originalMessage);
        System.out.println("Result:" + firstResult);

        Message lastResult = complexProcessor.handle(firstResult);
        System.out.println("Result:" + lastResult);

        Message totalResult = complexProcessor.handle(lastResult);
        System.out.println("Result:" + totalResult);

        complexProcessor.removeListener(listenerHistory);
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
    }
}
