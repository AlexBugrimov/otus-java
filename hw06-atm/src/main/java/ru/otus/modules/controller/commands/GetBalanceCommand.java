package ru.otus.modules.controller.commands;

import ru.otus.modules.controller.ControlBlock;
import ru.otus.modules.safe.DepositBox;
import ru.otus.tinkoff.Modules;

public class GetBalanceCommand implements Command {

    @Override
    public Result execute(Modules modules) {
        return new Result(
                true,
                String.format("В банкомате сумма: %s", getBalance(modules.getControlBlock(), modules.getDepositBox())));
    }

    private String getBalance(ControlBlock controlBlock, DepositBox depositBox) {
        return controlBlock.getBalance(depositBox);
    }
}
