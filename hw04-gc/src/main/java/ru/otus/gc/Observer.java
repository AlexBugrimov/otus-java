package ru.otus.gc;

import java.io.PrintStream;

public interface Observer {

    Observer observe(Executor executor);

    void printResults(PrintStream printStream);
}
