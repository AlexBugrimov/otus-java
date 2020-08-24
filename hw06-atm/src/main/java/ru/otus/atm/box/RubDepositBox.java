package ru.otus.atm.box;

import ru.otus.currency.Banknote;

import java.util.List;

public class RubDepositBox implements DepositBox {


    @Override
    public List<Banknote> pop() {
        return null;
    }

    @Override
    public void push(List<Banknote> banknotes) {

    }
}
