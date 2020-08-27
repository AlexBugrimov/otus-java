package ru.otus.safe;

import ru.otus.Banknote;

import java.util.List;

public interface DepositBox {

    boolean pushBanknotes(List<Banknote> giveOutBanknotes);

    String getBalance();

    List<Banknote> giveOutBanknotes(int amount);
}
