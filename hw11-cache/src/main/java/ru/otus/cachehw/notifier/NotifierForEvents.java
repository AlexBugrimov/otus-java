package ru.otus.cachehw.notifier;

import ru.otus.listeners.Listener;
import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class NotifierForEvents<K, V extends BaseEntity> implements Notifier<K, V> {

    private final List<Reference<Listener<K, V>>> listeners;

    public NotifierForEvents() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void notifyAllOf(Result<V> result) {
        listeners.forEach(listener -> notifyListenerOfResult(listener, result));
    }

    private void notifyListenerOfResult(Reference<Listener<K, V>> listener, Result<V> result) {
        final Listener<K, V> notified = listener.get();
        if (notified != null) {
            final V value = result.getValue();
            final K key = (K) value.getId();
            notified.notify(key, value, result.getAction());
        }
    }

    @Override
    public void addListener(Listener<K, V> listener) {
        var weakReference = new WeakReference<>(listener);
        listeners.add(weakReference);
    }

    @Override
    public void removeListener(Listener<K, V> listener) {
        var weakReference = new WeakReference<>(listener);
        listeners.remove(weakReference);
    }
}
