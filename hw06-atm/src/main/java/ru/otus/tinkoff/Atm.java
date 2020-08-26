package ru.otus.tinkoff;

import ru.otus.modules.controller.commands.Command;
import ru.otus.modules.controller.commands.Result;
import ru.otus.currency.Banknote;

import java.util.List;

public interface Atm {

    void depositMoney(List<Banknote> banknotes);

    List<Banknote> giveOutMoney();

    Result execute(Command command);
}
