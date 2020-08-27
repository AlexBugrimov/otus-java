package ru.otus.safe;

import ru.otus.Banknote;
import ru.otus.Ruble;
import ru.otus.exceptions.BalanceException;

import java.util.*;
import java.util.stream.Collectors;

public class RubleDepositBox implements DepositBox {

    private final SortedMap<Ruble.Nominal, Integer> buckets = new TreeMap<>(
            Comparator.comparing(Ruble.Nominal::getNumber).reversed());

    @Override
    public boolean pushBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(banknote -> {
            final Ruble.Nominal nominal = banknote.getNominal();
            final Integer count = buckets.getOrDefault(nominal, 0);
            buckets.put(nominal, count + 1);
        });
        return true;
    }

    @Override
    public String getBalance() {
        return String.format("%d %s", balance(), Currency.getInstance("RUB"));
    }

    private int balance() {
        return buckets.entrySet().stream()
                .map(bucket -> bucket.getKey().getNumber() * bucket.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Banknote> giveOutBanknotes(final int amount) {

        if (amount > balance()) {
            throw new BalanceException("В банкомате недостаточно средств");
        }
        final List<Banknote> banknotes = getAllBanknotes().stream()
                .filter(banknote -> banknote.getNominal().getNumber() < amount)
                .collect(Collectors.toList());

        int currentBanknote = 0;
        List<Banknote> result = new ArrayList<>();
        int []totalSum = {amount};
        while (currentBanknote <= banknotes.size() - 1) {
            final Ruble.Nominal nominal = banknotes.get(currentBanknote).getNominal();
            if (nominal.getNumber() > totalSum[0]) {
                currentBanknote++;
            } else {
                totalSum[0] -= nominal.getNumber();
                final Integer count = buckets.get(nominal);
                buckets.put(nominal, count - 1);
                result.add(new Ruble(nominal));
            }
        }
        if (totalSum[0] != 0) {
            throw new BalanceException(String.format("В банкомате нет купюр для выдачи суммы: %s", amount));
        }
        return result;
    }

    private List<Banknote> getAllBanknotes() {
        List<Banknote> banknotes = new LinkedList<>();
        buckets.forEach((nominal, count) -> {
            for (int i = 0; i < count; i++) {
                banknotes.add(new Ruble(nominal));
            }
        });
        return banknotes;
    }
}
