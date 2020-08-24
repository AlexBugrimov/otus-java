package ru.otus.currency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Ruble implements Banknote {

    private final Nominal nominal;

    @Override
    public int getNominal() {
        return nominal.getValue();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Nominal {

        HUNDRED(100),
        TWO_HUNDRED(200),
        FIVE_HUNDRED(500),
        THOUSAND(1000),
        FIVE_THOUSAND(5000);

        private final int value;
    }
}
