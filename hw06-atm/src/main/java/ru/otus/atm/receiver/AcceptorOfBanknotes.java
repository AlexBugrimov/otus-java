package ru.otus.atm.receiver;

import ru.otus.currency.Banknote;
import ru.otus.atm.shift.Source;

import java.util.List;

public interface AcceptorOfBanknotes extends Source {

    void doSwitchState();

    boolean acceptBanknotes(List<Banknote> banknotes);
}
