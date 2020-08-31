package ru.otus.tinkoff;

import ru.otus.controller.commands.Command;
import ru.otus.controller.commands.Result;
import ru.otus.Banknote;

import java.util.List;

public interface Atm {

    void depositMoney(List<Banknote> banknotes);

    List<Banknote> getMoney();

    Result execute(Command command);
}
