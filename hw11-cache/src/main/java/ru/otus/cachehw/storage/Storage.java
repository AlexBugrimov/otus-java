package ru.otus.cachehw.storage;

import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

public interface Storage<K, V extends BaseEntity> {

    Result<K, V> get(K key);
    Result<K, V> add(K key, V value);
    Result<K, V> remove(K key);
}
