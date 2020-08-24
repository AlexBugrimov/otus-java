package ru.otus.atm.receiver;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.currency.Banknote;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BoxAcceptorOfBanknotes implements AcceptorOfBanknotes {

    private State state;
    private List<Banknote> boxOfBanknotes;

    public BoxAcceptorOfBanknotes(State state, int boxSize) {
        this.state = state;
        this.boxOfBanknotes = new ArrayList<>(boxSize);
    }

    public void doSwitchState() {
        this.setState(state.next());
    }

    @Override
    public boolean acceptBanknotes(List<Banknote> banknotes) {
        if (banknotes.size() > boxOfBanknotes.size()) {
            System.out.println("Error");
        }
        return boxOfBanknotes.addAll(banknotes);
    }

    @Override
    public List<Banknote> pop() {
        return new ArrayList<>(boxOfBanknotes);
    }
}
