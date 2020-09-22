package ru.otus.gc;

import java.io.PrintStream;
import java.util.function.Supplier;

public class GcListener implements Listener {

    private final PrintStream printStream;

    public GcListener(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public Listener listen(Supplier<OutOfMemory> outOfMemoryConsumer) {
        outOfMemoryConsumer.get().toDo();
        return this;
    }

    @Override
    public void printResults() {
        printStream.println("Results");
    }
}
