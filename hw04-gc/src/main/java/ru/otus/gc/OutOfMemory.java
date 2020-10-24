package ru.otus.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory implements Executor {

    private static final Logger logger = LoggerFactory.getLogger(OutOfMemory.class);
    private static final int WAIT_MILLIS = 100;
    private final int limit;

    public OutOfMemory(int limit) {
        this.limit = limit;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void execute() {
        final List<String> collection = new ArrayList<>();
        for (; ; ) {
            for (int idx = 0; idx < limit; idx++) {
                collection.add(String.valueOf(idx));
            }
            for (int idx = 0; idx < collection.size() / 2; idx++) {
                collection.set(idx, null);
            }
            freeze();
        }
    }

    private static void freeze() {
        try {
            Thread.sleep(WAIT_MILLIS);
        } catch (InterruptedException e) {
            logger.error("Thread sleep error", e);
        }
    }
}
