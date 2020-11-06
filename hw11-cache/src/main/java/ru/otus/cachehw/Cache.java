package ru.otus.cachehw;

import ru.otus.listeners.Listener;

public interface Cache<K, V> {

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    void addListener(Listener<K, V> listener);

    void removeListener(Listener<K, V> listener);
}
