package ru.otus;

import ru.otus.gc.GcObserver;
import ru.otus.gc.OutOfMemory;

public class Main {

    public static void main(String[] args) {
        new GcObserver()
                .observe(new OutOfMemory(200_000))
                .printResults(System.out);
    }
}
