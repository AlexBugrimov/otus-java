package ru.otus.atm.control;

import ru.otus.atm.receiver.AcceptorOfBanknotes;
import ru.otus.atm.shift.Source;
import ru.otus.atm.shift.Target;

public class Computer implements ControlBlock {

    @Override
    public void doSwitch(AcceptorOfBanknotes acceptorOfBanknotes) {
        acceptorOfBanknotes.doSwitchState();
    }

    @Override
    public void transfer(Source from, Target to) {
        to.push(from.pop());
    }
}
