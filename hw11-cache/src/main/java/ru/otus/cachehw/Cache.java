package ru.otus.cachehw;

import ru.otus.listeners.Listener;

public interface Cache<K, V> {

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    void addListeners(Listener<K, V>... listener);

    void removeListeners(Listener<K, V>... listener);
}
