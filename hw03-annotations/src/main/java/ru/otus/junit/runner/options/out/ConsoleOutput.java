package ru.otus.junit.runner.options.out;

public class ConsoleOutput implements Output {

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
