package ru.otus.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminListener<K, V> implements Listener<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(AdminListener.class);

    @Override
    public void notify(K key, V value, String action) {
        logger.info("Info for Admin. key:{}, value:{}, action: {}", key, value, action);
    }
}
