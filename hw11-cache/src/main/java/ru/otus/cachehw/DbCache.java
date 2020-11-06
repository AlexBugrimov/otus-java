package ru.otus.cachehw;

import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.cachehw.notifier.Notifier;
import ru.otus.cachehw.notifier.NotifierForEvents;
import ru.otus.cachehw.storage.DataStorage;
import ru.otus.cachehw.storage.Storage;
import ru.otus.core.model.BaseEntity;
import ru.otus.listeners.Listener;

public class DbCache<K, V extends BaseEntity> implements Cache<K, V> {

    private final Storage<K, V> dataStorage = new DataStorage<>();
    private final Notifier<K, V> notifier = new NotifierForEvents<>();

    @Override
    public void put(K key, V value) {
        final Result<V> result = dataStorage.add(key, value);
        notifier.notifyAllOf(result);
    }

    @Override
    public void remove(K key) {
        final Result<V> result = dataStorage.remove(key);
        notifier.notifyAllOf(result);
    }

    @Override
    public V get(K key) {
        final Result<V> result = dataStorage.get(key);
        notifier.notifyAllOf(result);
        return result.getValue();
    }

    @Override
    public void addListener(Listener<K, V> listener) {
        notifier.addListener(listener);
    }

    @Override
    public void removeListener(Listener<K, V> listener) {
        notifier.removeListener(listener);
    }
}
