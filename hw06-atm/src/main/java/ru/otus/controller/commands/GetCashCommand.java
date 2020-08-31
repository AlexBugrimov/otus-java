package ru.otus.controller.commands;

import ru.otus.controller.ControlBlock;
import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.exceptions.BalanceException;
import ru.otus.safe.DepositBox;

public class GetCashCommand implements Command {

    private final int amount;

    public GetCashCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public Result execute(DispenserForBanknotes dispenser,
                          ControlBlock controlBlock,
                          DepositBox depositBox) {
        try {
            return new Result(
                    controlBlock.toShiftBanknote(amount, depositBox, dispenser),
                    String.format("Выдана сумма: %d", amount)
            );
        } catch (BalanceException ex) {
            return new Result(false, ex.getMessage());
        }
    }
}
