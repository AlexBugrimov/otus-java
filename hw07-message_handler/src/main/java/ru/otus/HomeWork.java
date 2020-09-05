package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.Logbook;
import ru.otus.listener.ListenerLogbook;
import ru.otus.listener.Record;
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
        Logbook listenerLogbook = new ListenerLogbook();
        complexProcessor.addListener(listenerLogbook);

        final Message originalMessage = new Message.Builder()
                .field11("field11")
                .field12("field12")
                .field13("field13")
                .build();
        Message firstResult = complexProcessor.handle(originalMessage);
        System.out.println("Result:" + firstResult);
        List<Record> records = listenerLogbook.getRecords();
        Message lastResult = complexProcessor.handle(firstResult);
        System.out.println("Result:" + lastResult);
        records = listenerLogbook.getRecords();

        Message totalResult = complexProcessor.handle(lastResult);
        System.out.println("Result:" + totalResult);
        records = listenerLogbook.getRecords();
        complexProcessor.removeListener(listenerLogbook);
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
    }
}
