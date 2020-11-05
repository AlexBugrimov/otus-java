package ru.otus.cachehw.storage;

public interface Storage<I, V> {

    Operation.Result<V> get(I id);
    Operation.Result<V> add(I id, V value);
    Operation.Result<V> remove(I id);
}
