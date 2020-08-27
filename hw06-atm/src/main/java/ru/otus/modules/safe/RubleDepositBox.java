package ru.otus.modules.safe;

import ru.otus.currency.Banknote;
import ru.otus.currency.Ruble;
import ru.otus.exceptions.BalanceException;

import java.util.*;
import java.util.stream.Stream;

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
        List<Banknote> banknotes = new LinkedList<>();
        box.forEach((nominal, count) -> {
            for (int i = 0; i < count; i++) {
                banknotes.add(new Ruble(nominal));
            }
        });
        int currentBanknote = 0;
        List<Banknote> result = new ArrayList<>();
        while (currentBanknote <= banknotes.size() - 1) {
            final Ruble.Nominal nominal = banknotes.get(currentBanknote).getNominal();
            if (nominal.getNumber() > amount) {
                currentBanknote++;
            } else {
                amount -= nominal.getNumber();
                final Integer count = box.get(nominal);
                box.put(nominal, count - 1);
                result.add(new Ruble(nominal));
            }
        }
        if (amount != 0) {
            throw new BalanceException(String.format("В банкомате нет купюр для выдачи суммы: %s", amount));
        }
        return result;
    }

}
