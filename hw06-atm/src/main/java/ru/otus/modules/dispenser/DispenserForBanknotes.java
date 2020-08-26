package ru.otus.modules.dispenser;

import ru.otus.currency.Banknote;

import java.util.List;

public interface DispenserForBanknotes {

    void putBanknotes(List<Banknote> banknotes);

    List<Banknote> giveOutBanknotes();

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
