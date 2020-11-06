package ru.otus.cachehw.storage;

import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

public interface Storage<K, V extends BaseEntity> {

    Result<V> get(K key);
    Result<V> add(K key, V value);
    Result<V> remove(K key);
}
