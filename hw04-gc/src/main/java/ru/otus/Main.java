package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.gc.GcObserver;
import ru.otus.gc.Observer;
import ru.otus.gc.OutOfMemory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final Observer observer = new GcObserver();
        try {
            observer.observe(new OutOfMemory(10_000));
        } finally {
            logger.info("Result: {}", observer.getResults());
        }
    }
}
