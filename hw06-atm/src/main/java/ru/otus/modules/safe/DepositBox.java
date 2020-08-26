package ru.otus.modules.safe;

import ru.otus.currency.Banknote;

import java.util.List;

public interface DepositBox {

    boolean pushBanknotes(List<Banknote> giveOutBanknotes);

    String getBalance();

    List<Banknote> giveOutBanknotes(int amount);
}
