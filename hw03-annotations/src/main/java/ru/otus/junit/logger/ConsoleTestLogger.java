package ru.otus.junit.logger;

public class ConsoleTestLogger implements TestLogger {

    @Override
    public void outTestResult(String text) {
        System.out.println(text);
    }
}
