package ru.otus.atm.shift;

import ru.otus.currency.Banknote;

import java.util.List;

public interface Source {

    List<Banknote> pop();
}