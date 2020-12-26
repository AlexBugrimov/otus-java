package ru.otus;

public class App {

    public static void main(String[] args) {

        final Runnable sequenceProcess = new SequenceProcess();

        new Thread(sequenceProcess, "Поток 1").start();
        new Thread(sequenceProcess, "Поток 2").start();
    }
}
