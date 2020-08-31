package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.Listener;
import ru.otus.listener.ListenerHistory;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorSwapFields;
import ru.otus.processor.ProcessorWithException;
import ru.otus.processor.exceptions.TimeParityException;

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
                new ProcessorSwapFields(),
                new ProcessorWithException());

        final ComplexProcessor complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TimeParityException(ex);
        });
        Listener listenerHistory = new ListenerHistory();
        complexProcessor.addListener(listenerHistory);

        final Message message = new Message.Builder()
                .field11("field11")
                .field12("field12")
                .field13("field13")
                .build();

        Message result = complexProcessor.handle(message);
        System.out.println("result:" + result);
        complexProcessor.removeListener(listenerHistory);
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
    }
}
