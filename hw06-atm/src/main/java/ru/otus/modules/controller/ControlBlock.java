package ru.otus.modules.controller;

import ru.otus.modules.dispenser.DispenserForBanknotes;
import ru.otus.modules.safe.DepositBox;

public interface ControlBlock {

    String getBalance(DepositBox depositBox);

    boolean passBanknotes(DispenserForBanknotes dispenser, DepositBox depositBox);

    boolean passBanknotes(int amount, DepositBox depositBox, DispenserForBanknotes dispenser);
}
