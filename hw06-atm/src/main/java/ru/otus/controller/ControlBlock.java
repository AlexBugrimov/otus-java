package ru.otus.controller;

import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

public interface ControlBlock {

    String getBalance(DepositBox depositBox);

    boolean toShiftBanknote(DispenserForBanknotes dispenser, DepositBox depositBox);

    boolean toShiftBanknote(int amount, DepositBox depositBox, DispenserForBanknotes dispenser);
}
