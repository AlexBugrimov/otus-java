package ru.otus;

import ru.otus.controller.PowerfulControlBlock;
import ru.otus.controller.commands.GetBalanceCommand;
import ru.otus.controller.commands.GetCashCommand;
import ru.otus.controller.commands.Result;
import ru.otus.controller.commands.TakeMoneyCommand;
import ru.otus.dispenser.BoxDispenserForBanknotes;
import ru.otus.dispenser.DispenserForBanknotes.Size;
import ru.otus.safe.RubleDepositBox;
import ru.otus.tinkoff.Atm;
import ru.otus.tinkoff.TinkoffAtm;

import java.util.Arrays;
import java.util.List;

import static ru.otus.Rub.*;

public class EntryPoint {

    public static void main(String[] args) {
        final Atm tinkoffAtm = new TinkoffAtm(
                new BoxDispenserForBanknotes(Size.SMALL),
                new PowerfulControlBlock(),
                new RubleDepositBox()
        );

        final List<Banknote> banknotes = Arrays.asList(
                RUB_100, RUB_100, RUB_100, RUB_100,
                RUB_1000, RUB_1000, RUB_1000, RUB_1000, RUB_1000,
                RUB_5000, RUB_5000,
                RUB_500, RUB_500,
                RUB_200, RUB_200, RUB_200
        );

        // Положить деньги в приемник
        tinkoffAtm.depositMoney(banknotes);
        // Положить деньги
        final Result takeMoney = tinkoffAtm.execute(new TakeMoneyCommand());
        System.out.println("Команда (внести деньги): " + takeMoney);

        final Result balanceBefore = tinkoffAtm.execute(new GetBalanceCommand());
        System.out.println("Команда (получить баланс): " + balanceBefore);

        // Выдать сумму в размере
        final Result cash = tinkoffAtm.execute(new GetCashCommand(3_600));
        System.out.println("Команда (выдать сумму): " + cash);

        // Забрать деньги
        final List<Banknote> money = tinkoffAtm.getMoney();
        System.out.println("Получение денег: " + money);

        final Result balanceAfter = tinkoffAtm.execute(new GetBalanceCommand());
        System.out.println("Команда (получить баланс): " + balanceAfter);
    }
}
