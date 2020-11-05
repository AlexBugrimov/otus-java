package ru.otus.cachehw.notifier;

import ru.otus.cachehw.Listener;
import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

public interface Notifier<I, V extends BaseEntity> {

    void notifyAllOf(Result<V> result);
    void addListener(Listener<I, V> listener);
    void removeListener(Listener<I, V> listener);
}
