package ru.otus.gc;

import java.util.function.Supplier;

public interface Listener {

    void printResults();

    Listener listen(Supplier<OutOfMemory> outOfMemorySupplier);
}
