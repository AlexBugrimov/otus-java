package ru.otus.atm;

import lombok.RequiredArgsConstructor;
import ru.otus.currency.Banknote;
import ru.otus.atm.box.DepositBox;
import ru.otus.atm.control.ControlBlock;
import ru.otus.atm.payer.PayerOfBanknotes;
import ru.otus.atm.receiver.AcceptorOfBanknotes;

import java.util.List;

@RequiredArgsConstructor
public class Atm {

    private final AcceptorOfBanknotes acceptor;
    private final ControlBlock controlBlock;
    private final DepositBox depositBox;
    private final PayerOfBanknotes payerOfBanknotes;

    public void acceptBanknotes(List<Banknote> banknotes) {
        if (acceptor.acceptBanknotes(banknotes)) {
            controlBlock.doSwitch(acceptor);
            controlBlock.transfer(acceptor, depositBox);
        }

    }

    public void startAcceptingBanknotes() {
        controlBlock.doSwitch(acceptor);
    }
}
