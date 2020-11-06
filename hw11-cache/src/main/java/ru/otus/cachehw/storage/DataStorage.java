package ru.otus.cachehw.storage;

import ru.otus.cachehw.storage.Operation.*;
import ru.otus.core.model.BaseEntity;

import java.util.Map;
import java.util.WeakHashMap;

import static ru.otus.cachehw.storage.Operation.*;

public final class DataStorage<K, V extends BaseEntity> implements Storage<K, V> {

    private final Map<K, V> entities;

    public DataStorage() {
        this.entities = new WeakHashMap<>();
    }

    @Override
    public Result<K, V> get(K key) {
        return RECEIVING.execute(key, entities.get(key));
    }

    @Override
    public Result<K, V> add(K key, V value) {
        return ADDITION.execute(key, entities.put(key, value));
    }

    @Override
    public Result<K, V> remove(K key) {
        return REMOVAL.execute(key, entities.remove(key));
    }
}
