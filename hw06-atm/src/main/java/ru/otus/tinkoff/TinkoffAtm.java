package ru.otus.tinkoff;

import ru.otus.modules.controller.commands.Command;
import ru.otus.modules.controller.commands.Result;
import ru.otus.currency.Banknote;
import ru.otus.modules.dispenser.DispenserForBanknotes;

import java.util.List;

public class TinkoffAtm implements Atm {

    private final Modules modules;

    public TinkoffAtm(Modules modules) {
        this.modules = modules;
    }

    @Override
    public void depositMoney(List<Banknote> banknotes) {
        getDispenser().putBanknotes(banknotes);
    }

    @Override
    public List<Banknote> giveOutMoney() {
        return getDispenser().giveOutBanknotes();
    }

    @Override
    public Result execute(Command command) {
        return command.execute(modules);
    }

    private DispenserForBanknotes getDispenser() {
        return modules.getDispenser();
    }
}
