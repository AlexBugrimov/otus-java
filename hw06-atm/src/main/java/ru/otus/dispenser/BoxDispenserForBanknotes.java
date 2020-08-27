package ru.otus.dispenser;

import ru.otus.Banknote;

import java.util.*;

public class BoxDispenserForBanknotes implements DispenserForBanknotes {

    private final List<Banknote> banknotes;

    public BoxDispenserForBanknotes(Size size) {
        this.banknotes = new ArrayList<>(size.getQuantity());
    }

    @Override
    public void addBanknotes(List<Banknote> banknotes) {
        this.banknotes.addAll(banknotes);
    }

    @Override
    public List<Banknote> getBanknotes() {
        final List<Banknote> banknotes = new ArrayList<>(this.banknotes);
        this.banknotes.clear();
        return banknotes;
    }

    @Override
    public boolean pushBanknotes(List<Banknote> banknotes) {
        return this.banknotes.addAll(banknotes);
    }
}
