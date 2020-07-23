package ru.otus.junit;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        final Runner runner = new OtusRunner(new ClassLoader("."));
//        runner.run();
        final File file = new File("src/");
        System.out.println(file);
    }
}
