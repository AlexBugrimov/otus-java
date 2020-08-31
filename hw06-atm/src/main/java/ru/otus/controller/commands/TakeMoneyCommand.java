package ru.otus.controller.commands;

import ru.otus.controller.ControlBlock;
import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

public class TakeMoneyCommand implements Command {

    @Override
    public Result execute(DispenserForBanknotes dispenser,
                          ControlBlock controlBlock,
                          DepositBox depositBox) {
        return new Result(controlBlock.toShiftBanknote(dispenser, depositBox),"Купюры загружены в банкомат");
    }
}
