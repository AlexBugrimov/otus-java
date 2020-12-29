package ru.otus;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class SequenceProcess implements Runnable {

    private static final int LOWER_LIMIT = 1;
    private static final int UPPER_LIMIT = 11;
    private String lastThread = "Поток №2";

    @Override
    public void run() {
        while (true) {
            iterate(range(LOWER_LIMIT, UPPER_LIMIT));
            sleepMilliseconds(1000);
            iterate(range(LOWER_LIMIT, UPPER_LIMIT).map(i -> UPPER_LIMIT - i));
        }
    }

    private void iterate(IntStream range) {
        range.forEach(value -> {
                    synchronized (this) {
                        try {
                            while (currentThreadName().equals(lastThread)) {
                                this.wait();
                            }
                            printValue(value);
                            lastThread = currentThreadName();
                            sleepMilliseconds(500);
                            notifyAll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
    }

    private static String currentThreadName() {
        return Thread.currentThread().getName();
    }

    private void printValue(int value) {
        System.out.printf("[ %s ] - %d", currentThreadName(), value);
        System.out.println();
    }

    private void sleepMilliseconds(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptedException(e);
        }
    }
}
