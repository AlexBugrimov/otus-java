package ru.otus;

import ru.otus.atm.Atm;
import ru.otus.atm.box.RubDepositBox;
import ru.otus.atm.control.Computer;
import ru.otus.atm.payer.AutoPayerOfBanknotes;
import ru.otus.atm.receiver.BoxReceiverOfBanknotes;
import ru.otus.atm.receiver.State;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int BOX_RECEIVER_SIZE = 50;

    public static void main(String[] args) {
        final Atm atm = new Atm(
                new BoxReceiverOfBanknotes(State.CLOSE, BOX_RECEIVER_SIZE),
                new Computer(),
                new RubDepositBox(),
                new AutoPayerOfBanknotes()
        );

        List<Banknote> twentyThousandRubles = new ArrayList<>();

        atm.startAcceptingBanknotes();
        atm.acceptBanknotes(twentyThousandRubles);
    }
}
