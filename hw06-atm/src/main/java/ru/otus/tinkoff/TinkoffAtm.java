package ru.otus.tinkoff;

import ru.otus.controller.ControlBlock;
import ru.otus.controller.commands.Command;
import ru.otus.controller.commands.Result;
import ru.otus.Banknote;
import ru.otus.dispenser.DispenserForBanknotes;
import ru.otus.safe.DepositBox;

import java.util.List;

public class TinkoffAtm implements Atm {

    private final DispenserForBanknotes dispenser;
    private final ControlBlock controlBlock;
    private final DepositBox depositBox;

    public TinkoffAtm(DispenserForBanknotes dispenser,
                      ControlBlock controlBlock,
                      DepositBox depositBox) {
        this.dispenser = dispenser;
        this.controlBlock = controlBlock;
        this.depositBox = depositBox;
    }

    @Override
    public void depositMoney(List<Banknote> banknotes) {
        dispenser.addBanknotes(banknotes);
    }

    @Override
    public List<Banknote> getMoney() {
        return dispenser.getBanknotes();
    }

    @Override
    public Result execute(Command command) {
        return command.execute(dispenser, controlBlock, depositBox);
    }
}
