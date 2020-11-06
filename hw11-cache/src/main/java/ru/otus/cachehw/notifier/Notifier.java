package ru.otus.cachehw.notifier;

import ru.otus.listeners.Listener;
import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

public interface Notifier<K, V extends BaseEntity> {

    void notifyAllOf(Result<V> result);
    void addListener(Listener<K, V> listener);
    void removeListener(Listener<K, V> listener);
}
