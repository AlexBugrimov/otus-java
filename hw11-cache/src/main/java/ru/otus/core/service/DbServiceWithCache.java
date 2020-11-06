package ru.otus.core.service;

import ru.otus.cachehw.Cache;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.BaseEntity;

import java.util.Optional;

public class DbServiceWithCache<V extends BaseEntity, K> implements DbService<V, K> {

    private final Cache<K, V> cache;
    private final DbService<V, K> dbService;

    public DbServiceWithCache(Dao<V, K> dao, Cache<K, V> cache) {
        this.cache = cache;
        this.dbService = new DbServiceImpl<>(dao);
    }

    @Override
    public K save(V value) {
        final K savedKey = dbService.save(value);
        cache.put(savedKey, value);
        return savedKey;
    }

    @Override
    public Optional<V> getById(K id) {
        final V value = cache.get(id);
        if (value == null) {
            return dbService.getById(id);
        }
        return Optional.of(value);
    }
}
