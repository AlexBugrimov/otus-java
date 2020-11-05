package ru.otus.cachehw.notifier;

import ru.otus.cachehw.Listener;
import ru.otus.cachehw.storage.Operation.Result;
import ru.otus.core.model.BaseEntity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class NotifierForEvents<I, V extends BaseEntity> implements Notifier<I, V> {

    private final List<Reference<Listener<I, V>>> listeners;

    public NotifierForEvents() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void notifyAllOf(Result<V> result) {
        listeners.forEach(listener -> {
            final Listener<I, V> notified = listener.get();
            if (notified != null) {
                final V content = result.getContent();
                final I id = (I) content.getId();
                notified.notify(id, content, result.getMessage());
            }
        });
    }

    @Override
    public void addListener(Listener<I, V> listener) {
        var weakReference = new WeakReference<>(listener);
        listeners.add(weakReference);
    }

    @Override
    public void removeListener(Listener<I, V> listener) {
        var weakReference = new WeakReference<>(listener);
        listeners.remove(weakReference);
    }
}
