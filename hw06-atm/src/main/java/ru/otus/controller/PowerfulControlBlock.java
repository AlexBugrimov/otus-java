package ru.otus.controller;

import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

public class PowerfulControlBlock implements ControlBlock {

    @Override
    public boolean toShiftBanknote(DispenserForBanknotes dispenser, DepositBox depositBox) {
        return depositBox.pushBanknotes(dispenser.getBanknotes());
    }

    @Override
    public boolean toShiftBanknote(int amount, DepositBox depositBox, DispenserForBanknotes dispenser) {
        return dispenser.pushBanknotes(depositBox.giveOutBanknotes(amount));
    }

    @Override
    public String getBalance(DepositBox depositBox) {
        return depositBox.getBalance();
    }
}
