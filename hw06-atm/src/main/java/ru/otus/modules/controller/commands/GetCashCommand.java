package ru.otus.modules.controller.commands;


import ru.otus.exceptions.BalanceException;
import ru.otus.modules.controller.ControlBlock;
import ru.otus.modules.dispenser.DispenserForBanknotes;
import ru.otus.modules.safe.DepositBox;
import ru.otus.tinkoff.Modules;

public class GetCashCommand implements Command {

    private final int amount;

    public GetCashCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public Result execute(Modules modules) {
        final DispenserForBanknotes dispenser = modules.getDispenser();
        final DepositBox depositBox = modules.getDepositBox();
        try {
            return new Result(
                    getControlBlock(modules).passBanknotes(amount, depositBox, dispenser),
                    String.format("Выдана сумма: %d", amount)
            );
        } catch (BalanceException ex) {
            return new Result(
                    false,
                    ex.getMessage()
            );
        }
    }

    private ControlBlock getControlBlock(Modules modules) {
        return modules.getControlBlock();
    }
}
