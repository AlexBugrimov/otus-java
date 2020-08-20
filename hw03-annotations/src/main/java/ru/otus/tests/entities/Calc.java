package ru.otus.tests.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Calc {

    private int num1;
    private int num2;

    public int add() {
        return num1 + num2;
    }

    public int sub() {
        return num1 - num2;
    }

    public int mul() {
        return num1 * num2;
    }

    public int div() {
        return num1 / num2;
    }

    public void clean() {
        this.num1 = 0;
        this.num2 = 0;
    }

    public Calc setNum2(int num2) {
        this.num2 = num2;
        return this;
    }
}
