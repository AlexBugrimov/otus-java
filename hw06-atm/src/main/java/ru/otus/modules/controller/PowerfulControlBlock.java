package ru.otus.modules.controller;

import ru.otus.modules.dispenser.DispenserForBanknotes;
import ru.otus.modules.safe.DepositBox;

public class PowerfulControlBlock implements ControlBlock {

    @Override
    public boolean passBanknotes(DispenserForBanknotes dispenser, DepositBox depositBox) {
        return depositBox.pushBanknotes(dispenser.giveOutBanknotes());
    }

    @Override
    public boolean passBanknotes(int amount, DepositBox depositBox, DispenserForBanknotes dispenser) {
        return dispenser.pushBanknotes(depositBox.giveOutBanknotes(amount));
    }

    @Override
    public String getBalance(DepositBox depositBox) {
        return depositBox.getBalance();
    }
}
