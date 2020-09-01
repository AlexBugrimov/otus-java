package ru.otus;

import ru.otus.calculator.Calculator;
import ru.otus.calculator.EngineeringCalculator;
import ru.otus.calculator.SimpleCalculator;
import ru.otus.proxy.Ioc;

public class Main {

    public static void main(String[] args) {

        final Class<Calculator> calculatorClass = Calculator.class;

        final Calculator engineeringCalculator = Ioc.createWithLogging(calculatorClass, new EngineeringCalculator());
        engineeringCalculator.calculation(1);
        engineeringCalculator.calculation(2, 10);
        engineeringCalculator.calculation(11, 1000, "Тестовое сообщение (engineeringCalculator)");

        final Calculator simpleCalculator = Ioc.createWithLogging(calculatorClass, new SimpleCalculator());
        simpleCalculator.calculation(7);
        simpleCalculator.calculation(92, 100);
        simpleCalculator.calculation(0, 10, "Тестовое сообщение (simpleCalculator)");
    }
}
