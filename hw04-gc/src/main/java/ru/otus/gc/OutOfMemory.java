package ru.otus.gc;

import java.util.function.Supplier;

public class OutOfMemory implements Supplier<OutOfMemory> {

    @Override
    public OutOfMemory get() {
        return this;
    }

    public void toDo() {

    }
}
