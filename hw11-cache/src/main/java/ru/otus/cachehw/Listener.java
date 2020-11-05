package ru.otus.cachehw;

public interface Listener<K, V> {

    void notify(K key, V value, String action);
}
