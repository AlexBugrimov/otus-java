package ru.otus.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.db.dao.Dao;

import java.util.Optional;

public class DbServiceImpl<T> implements DbService<T> {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);

    private final Dao<T> dao;

    public DbServiceImpl(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public long save(T t) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var id = dao.insert(t);
                sessionManager.commitSession();

                logger.info("created entity: {}", id);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> getById(long id) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> entityOptional = dao.findById(id);

                logger.info("entity: {}", entityOptional.orElse(null));
                return entityOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
