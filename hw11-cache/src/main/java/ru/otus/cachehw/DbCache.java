package ru.otus.cachehw;

import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.cachehw.notifier.Notifier;
import ru.otus.cachehw.notifier.NotifierForEvents;
import ru.otus.cachehw.storage.DataStorage;
import ru.otus.cachehw.storage.Storage;
import ru.otus.core.model.BaseEntity;

public class DbCache<I, V extends BaseEntity> implements Cache<I, V> {

    private final Storage<I, V> dataStorage = new DataStorage<>();
    private final Notifier<I, V> notifier = new NotifierForEvents<>();

    @Override
    public void put(I key, V value) {
        final Result<V> result = dataStorage.add(key, value);
        notifier.notifyAllOf(result);
    }

    @Override
    public void remove(I id) {
        final Result<V> result = dataStorage.remove(id);
        notifier.notifyAllOf(result);
    }

    @Override
    public V get(I key) {
        final Result<V> result = dataStorage.get(key);
        notifier.notifyAllOf(result);
        return result.getContent();
    }

    @Override
    public void addListener(Listener<I, V> listener) {
        notifier.addListener(listener);
    }

    @Override
    public void removeListener(Listener<I, V> listener) {
        notifier.removeListener(listener);
    }
}
