package ru.otus.safe;

import ru.otus.Banknote;
import ru.otus.exceptions.BalanceException;

import java.util.*;
import java.util.stream.Collectors;

public class RubleDepositBox implements DepositBox {

    private final SortedMap<Banknote, Integer> buckets = new TreeMap<>(
            Comparator.comparing(Banknote::getNominal).reversed());

    @Override
    public boolean pushBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(banknote -> {
            final Integer count = buckets.getOrDefault(banknote, 0);
            buckets.put(banknote, count + 1);
        });
        return true;
    }

    @Override
    public String getBalance() {
        return String.format("%d %s", balance(), Currency.getInstance("RUB"));
    }

    private int balance() {
        return buckets.entrySet().stream()
                .map(bucket -> bucket.getKey().getNominal() * bucket.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Banknote> giveOutBanknotes(final int amount) {

        if (amount > balance()) {
            throw new BalanceException("В банкомате недостаточно средств");
        }
        final List<Banknote> banknotes = getAllBanknotes().stream()
                .filter(banknote -> banknote.getNominal() < amount)
                .collect(Collectors.toList());

        int currentBanknote = 0;
        List<Banknote> result = new ArrayList<>();
        int []totalSum = {amount};
        while (currentBanknote <= banknotes.size() - 1) {
            final Banknote banknote = banknotes.get(currentBanknote);
            final int nominal = banknote.getNominal();
            if (nominal > totalSum[0]) {
                currentBanknote++;
            } else {
                totalSum[0] -= nominal;
                final Integer count = buckets.get(banknote);
                buckets.put(banknote, count - 1);
                result.add(banknote);
            }
        }
        if (totalSum[0] != 0) {
            throw new BalanceException(String.format("В банкомате нет купюр для выдачи суммы: %s", amount));
        }
        return result;
    }

    private List<Banknote> getAllBanknotes() {
        List<Banknote> banknotes = new LinkedList<>();
        buckets.forEach((banknote, count) -> {
            for (int i = 0; i < count; i++) {
                banknotes.add(banknote);
            }
        });
        return banknotes;
    }
}
