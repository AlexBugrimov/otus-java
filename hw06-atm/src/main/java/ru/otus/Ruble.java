package ru.otus;

public class Ruble implements Banknote {

    private final Nominal nominal;

    public Ruble(Nominal nominal) {
        this.nominal = nominal;
    }

    @Override
    public Nominal getNominal() {
        return nominal;
    }

    public enum Nominal {

        HUNDRED(100),
        TWO_HUNDRED(200),
        FIVE_HUNDRED(500),
        THOUSAND(1000),
        FIVE_THOUSAND(5000);

        private final Integer number;

        Nominal(Integer number) {
            this.number = number;
        }

        public Integer getNumber() {
            return this.number;
        }

        @Override
        public String toString() {
            return number.toString();
        }
    }

    @Override
    public String toString() {
        return nominal.toString() + "руб.";
    }
}
