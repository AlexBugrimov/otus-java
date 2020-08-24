package ru.otus.atm.control;

import ru.otus.atm.receiver.AcceptorOfBanknotes;

public interface AcceptingBanknotes {

    void doSwitch(AcceptorOfBanknotes acceptorOfBanknotes);

}
