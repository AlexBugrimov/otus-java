package ru.otus.atm.shift;

import ru.otus.Banknote;

import java.util.List;

public interface Target {

    void push(List<Banknote> banknotes);
}
