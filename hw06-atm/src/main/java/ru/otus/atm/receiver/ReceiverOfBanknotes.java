package ru.otus.atm.receiver;

import ru.otus.Banknote;
import ru.otus.atm.shift.Source;

import java.util.List;

public interface ReceiverOfBanknotes extends Source {

    void doSwitchState();

    boolean acceptBanknotes(List<Banknote> banknotes);
}
