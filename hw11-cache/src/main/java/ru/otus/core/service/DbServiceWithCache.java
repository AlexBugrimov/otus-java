package ru.otus.core.service;

import ru.otus.cachehw.Cache;
import ru.otus.cachehw.DbCache;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.BaseEntity;

import java.util.Optional;

public class DbServiceWithCache<T extends BaseEntity, K extends Number> extends DbServiceImpl<T> {

    private final Cache<K, T> cache;

    public DbServiceWithCache(Dao<T> dao) {
        super(dao);
        this.cache = new DbCache<>();
    }

    @Override
    public long save(T t) {
        cache.put((K) t.getId(), t);
        return super.save(t);
    }

    @Override
    public Optional<T> getById(long id) {
        return super.getById(id);
    }
}
