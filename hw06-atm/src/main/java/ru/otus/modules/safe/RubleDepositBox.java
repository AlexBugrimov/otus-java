package ru.otus.modules.safe;

import ru.otus.currency.Banknote;
import ru.otus.currency.Ruble;
import ru.otus.exceptions.BalanceException;

import java.util.*;

public class RubleDepositBox implements DepositBox {

    private final SortedMap<Ruble.Nominal, Integer> box = new TreeMap<>(Comparator.comparing(Ruble.Nominal::getNumber).reversed());

    @Override
    public boolean pushBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(banknote -> {
            final Ruble.Nominal nominal = banknote.getNominal();
            final Integer count = box.getOrDefault(nominal, 0);
            box.put(nominal, count + 1);
        });
        return true;
    }

    @Override
    public String getBalance() {
        return String.format("%d %s", balance(), Currency.getInstance("RUB"));
    }

    private int balance() {
        return box.entrySet().stream()
                .map(bucket -> bucket.getKey().getNumber() * bucket.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Banknote> giveOutBanknotes(int amount) {

        if (amount > balance()) {
            throw new BalanceException("В банкомате недостаточно средств");
        }


        return null;
    }

}
