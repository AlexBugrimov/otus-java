package ru.otus.calculator;

import ru.otus.proxy.Log;

public class EngineeringCalculator implements Calculator {

    @Log
    @Override
    public void calculation(int number) {
        System.out.println("\tClass EngineeringCalculator, Method: calculation(int number)");
    }

    @Log
    @Override
    public void calculation(int numberOne, int numberTwo) {
        System.out.println("\tClass EngineeringCalculator, Method: calculation(int numberOne, int numberTwo)");
    }

    @Log
    @Override
    public void calculation(int numberOne, int numberTwo, String message) {
        System.out.println("\tClass EngineeringCalculator, Method: calculation(int numberOne, int numberTwo, String message)");
    }
}
