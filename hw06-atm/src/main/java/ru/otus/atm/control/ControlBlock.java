package ru.otus.atm.control;

import ru.otus.atm.shift.Source;
import ru.otus.atm.shift.Target;

public interface ControlBlock extends AcceptingBanknotes {

    void shift(Source from, Target to);
}
