package ru.otus;

public enum Rub implements Banknote {

    RUB_100(100),
    RUB_200(200),
    RUB_500(500),
    RUB_1000(1000),
    RUB_5000(5000);

    private final int nominal;

    Rub(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
