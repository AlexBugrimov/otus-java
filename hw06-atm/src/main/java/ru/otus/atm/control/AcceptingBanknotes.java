package ru.otus.atm.control;

import ru.otus.atm.receiver.ReceiverOfBanknotes;

public interface AcceptingBanknotes {

    void doSwitch(ReceiverOfBanknotes receiverOfBanknotes);

}
