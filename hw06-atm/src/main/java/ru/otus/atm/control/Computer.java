package ru.otus.atm.control;

import ru.otus.atm.receiver.ReceiverOfBanknotes;
import ru.otus.atm.shift.Source;
import ru.otus.atm.shift.Target;

public class Computer implements ControlBlock {

    @Override
    public void doSwitch(ReceiverOfBanknotes receiverOfBanknotes) {
        receiverOfBanknotes.doSwitchState();
    }

    @Override
    public void shift(Source from, Target to) {
        to.push(from.pop());
    }
}
