package ru.otus.calculator;

public interface Calculator {

    void calculation(int number);

    void calculation(int numberOne, int numberTwo);

    void calculation(int numberOne, int numberTwo, String message);
}
