package ru.otus;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class SequenceProcess implements Runnable {

    private static final int FIRST = 1;
    private static final int LAST = 11;

    @Override
    public void run() {
        while (true) {
            iterate(range(FIRST, LAST));
            sleepForOneSecond();
            iterate(reverseRange(LAST, FIRST));
        }
    }

    private void iterate(IntStream intStream) {
        intStream.forEach(value -> {
                    synchronized (this) {
                        notifyAll();
                        System.out.println(Thread.currentThread().getName() + ": " + value);
                        theWait();
                    }
                }
        );
    }

    private void theWait() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new CustomInterruptedException(e);
        }
    }

    private void sleepForOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            throw new CustomInterruptedException(e);
        }
    }

    private static IntStream reverseRange(final int last, final int first) {
        return range(first, last).map(i -> last - i);
    }
}
