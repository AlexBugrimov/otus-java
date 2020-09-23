package ru.otus.gc;

import java.io.PrintStream;

public class GcObserver implements Observer {

    @Override
    public Observer observe(Executor executor) {
        executor.execute();
        return this;
    }

    @Override
    public void printResults(PrintStream printStream) {
        printStream.println("RESULT");
    }
}
