package ru.otus.modules.controller.commands;


import ru.otus.modules.controller.ControlBlock;
import ru.otus.modules.dispenser.DispenserForBanknotes;
import ru.otus.modules.safe.DepositBox;
import ru.otus.tinkoff.Modules;

public class TakeMoneyCommand implements Command {

    @Override
    public Result execute(Modules modules) {
        final DispenserForBanknotes dispenser = modules.getDispenser();
        final DepositBox depositBox = modules.getDepositBox();
        return new Result(
                getControlBlock(modules).passBanknotes(dispenser, depositBox),
                "Купюры загружены в банкомат"
        );
    }

    private ControlBlock getControlBlock(Modules modules) {
        return modules.getControlBlock();
    }
}
