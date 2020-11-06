package ru.otus.listeners;

public interface Listener<K, V> {

    void notify(K key, V value, String action);
}
