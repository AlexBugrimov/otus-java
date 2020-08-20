package ru.otus.junit.runner.options.out;

public class ConsoleOutput implements Output {

    @Override
    public void print(String template, Object...values) {
        System.out.printf(template, values);
    }
}
