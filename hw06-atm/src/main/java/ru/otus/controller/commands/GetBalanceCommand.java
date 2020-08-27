package ru.otus.controller.commands;

import ru.otus.controller.ControlBlock;
import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

public class GetBalanceCommand implements Command {

    @Override
    public Result execute(DispenserForBanknotes dispenser,
                          ControlBlock controlBlock,
                          DepositBox depositBox) {
        return new Result(
                true,
                String.format("В банкомате сумма: %s", controlBlock.getBalance(depositBox)));
    }
}
