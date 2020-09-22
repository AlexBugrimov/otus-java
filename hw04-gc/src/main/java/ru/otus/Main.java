package ru.otus;

import ru.otus.gc.GcListener;
import ru.otus.gc.OutOfMemory;

public class Main {

    public static void main(String[] args) {
        new GcListener(System.out)
                .listen(new OutOfMemory())
                .printResults();
    }
}
