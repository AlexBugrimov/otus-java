package ru.otus.cachehw.storage;

import ru.otus.cachehw.storage.Operation.*;

import java.util.Map;
import java.util.WeakHashMap;

import static ru.otus.cachehw.storage.Operation.*;

public class DataStorage<I, V> implements Storage<I, V> {

    private final Map<I, V> entities;

    public DataStorage() {
        this.entities = new WeakHashMap<>();
    }

    @Override
    public Result<V> get(I id) {
        return GET.execute(entities.get(id));
    }

    @Override
    public Result<V> add(I id, V value) {
        return ADD.execute(entities.put(id, value));
    }

    @Override
    public Result<V> remove(I id) {
        return REMOVE.execute(entities.remove(id));
    }
}
