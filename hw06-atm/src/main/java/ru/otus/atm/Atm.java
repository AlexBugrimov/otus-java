package ru.otus.atm;

import lombok.RequiredArgsConstructor;
import ru.otus.Banknote;
import ru.otus.atm.box.DepositBox;
import ru.otus.atm.control.ControlBlock;
import ru.otus.atm.payer.PayerOfBanknotes;
import ru.otus.atm.receiver.ReceiverOfBanknotes;

import java.util.List;

@RequiredArgsConstructor
public class Atm {

    private final ReceiverOfBanknotes receiverOfBanknotes;
    private final ControlBlock controlBlock;
    private final DepositBox depositBox;
    private final PayerOfBanknotes payerOfBanknotes;

    public void acceptBanknotes(List<Banknote> banknotes) {
        if (receiverOfBanknotes.acceptBanknotes(banknotes)) {
            controlBlock.doSwitch(receiverOfBanknotes);
            controlBlock.shift(receiverOfBanknotes, depositBox);
        }

    }

    public void startAcceptingBanknotes() {
        controlBlock.doSwitch(receiverOfBanknotes);
    }
}
