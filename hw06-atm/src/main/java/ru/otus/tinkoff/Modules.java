package ru.otus.tinkoff;

import ru.otus.modules.controller.ControlBlock;
import ru.otus.modules.dispenser.DispenserForBanknotes;
import ru.otus.modules.safe.DepositBox;

public class Modules {

    private final DispenserForBanknotes dispenser;
    private final ControlBlock controlBlock;
    private final DepositBox depositBox;

    public Modules(DispenserForBanknotes dispenser,
                   ControlBlock controlBlock,
                   DepositBox depositBox) {
        this.dispenser = dispenser;
        this.controlBlock = controlBlock;
        this.depositBox = depositBox;
    }

    public DispenserForBanknotes getDispenser() {
        return dispenser;
    }

    public ControlBlock getControlBlock() {
        return controlBlock;
    }

    public DepositBox getDepositBox() {
        return depositBox;
    }
}
