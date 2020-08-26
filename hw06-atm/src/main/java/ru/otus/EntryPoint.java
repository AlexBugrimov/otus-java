package ru.otus;

import ru.otus.modules.controller.PowerfulControlBlock;
import ru.otus.modules.controller.commands.GetBalanceCommand;
import ru.otus.modules.controller.commands.GetCashCommand;
import ru.otus.modules.controller.commands.Result;
import ru.otus.modules.controller.commands.TakeMoneyCommand;
import ru.otus.currency.Banknote;
import ru.otus.currency.Ruble;
import ru.otus.currency.Ruble.Nominal;
import ru.otus.modules.dispenser.BoxDispenserForBanknotes;
import ru.otus.modules.dispenser.DispenserForBanknotes.Size;
import ru.otus.modules.safe.RubleDepositBox;
import ru.otus.tinkoff.Atm;
import ru.otus.tinkoff.Modules;
import ru.otus.tinkoff.TinkoffAtm;

import java.util.Arrays;
import java.util.List;

public class EntryPoint {

    public static void main(String[] args) {
        final Atm tinkoffAtm = new TinkoffAtm(
                new Modules(
                        new BoxDispenserForBanknotes(Size.SMALL),
                        new PowerfulControlBlock(),
                        new RubleDepositBox()
                )
        );

        final List<Banknote> banknotes = Arrays.asList(
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.HUNDRED),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.FIVE_THOUSAND),
                new Ruble(Nominal.THOUSAND),
                new Ruble(Nominal.TWO_HUNDRED),
                new Ruble(Nominal.TWO_HUNDRED)
                );

        // Положить деньги в приемник
        tinkoffAtm.depositMoney(banknotes);
        // Положить деньги
        final Result takeMoneyResult = tinkoffAtm.execute(new TakeMoneyCommand());

        final Result getBalanceResult = tinkoffAtm.execute(new GetBalanceCommand());
        // Выдать сумму в размере
        final Result getCashResult = tinkoffAtm.execute(new GetCashCommand(3_600));
        // Забрать деньги
        final List<Banknote> money = tinkoffAtm.giveOutMoney();

        final Result result = tinkoffAtm.execute(new GetBalanceCommand());
    }
}
