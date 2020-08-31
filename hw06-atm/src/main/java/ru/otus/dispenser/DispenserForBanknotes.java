package ru.otus.dispenser;

import ru.otus.Banknote;

import java.util.List;

public interface DispenserForBanknotes {

    void addBanknotes(List<Banknote> banknotes);

    List<Banknote> getBanknotes();

    boolean pushBanknotes(List<Banknote> banknotes);

    enum Size {
        SMALL(20), BIG(200);

        Size(int quantity) {
            this.quantity = quantity;
        }

        private final int quantity;

        public int getQuantity() {
            return quantity;
        }
    }
}
