package ru.otus.cachehw;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;

public class DbCache<K, V> implements Cache<K, V> {

    private final Map<K, V> dataStorage = new WeakHashMap<>();
    private final List<Reference<HwListener<K, V>>> listenerStorage = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        dataStorage.put(key, value);
    }

    @Override
    public void remove(K key) {
        dataStorage.remove(key);
    }

    @Override
    public V get(K key) {
        return dataStorage.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listenerStorage.add(new WeakReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listenerStorage.remove(new WeakReference<>(listener));
    }
}
