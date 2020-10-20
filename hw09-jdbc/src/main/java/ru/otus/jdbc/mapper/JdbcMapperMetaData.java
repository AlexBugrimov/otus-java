package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.utils.ObjectHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public final class JdbcMapperMetaData<T> implements JdbcMapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMapperMetaData.class);

    private final SessionManager sessionManager;
    private final DbExecutor<T> executor;
    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperMetaData(SessionManager sessionManager,
                              DbExecutor<T> executor,
                              EntityClassMetaData<T> entityClassMetaData) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.entityClassMetaData = entityClassMetaData;
        this.entitySQLMetaData = EntitySQLMetaDataImpl.of(entityClassMetaData);
    }

    @Override
    public long insert(T entity) {
        final String sql = entitySQLMetaData.getInsertSql();
        try {
            return executor.executeInsert(getConnection(), sql, entityClassMetaData.getValues(entity));
        } catch (SQLException ex) {
            logger.error("Error adding entity: {}", entity, ex);
            return -1;
        }
    }

    @Override
    public void update(T entity) {
        final String sql = entitySQLMetaData.getUpdateSql();
        try {
            executor.executeInsert(getConnection(), sql, entityClassMetaData.getValues(entity));
        } catch (SQLException ex) {
            logger.error("Error updating entity: {}", entity, ex);
        }
    }

    @Override
    public void insertOrUpdate(T entity) {
        final T object = ObjectHandler.getEntity(entityClassMetaData, entity);
        var optionalEntity = this.findById(object);
        if (optionalEntity.isPresent()) {
            update(entity);
        } else {
            insert(entity);
        }
    }

    @Override
    public Optional<T> findById(Object id) {
        final String sql = entitySQLMetaData.getSelectByIdSql();
        final Function<ResultSet, T> getEntity = resultSet -> {
            try {
                if (resultSet.next()) {
                    return ObjectHandler.build(resultSet, entityClassMetaData);
                }
            } catch (SQLException ex) {
                logger.error("Error getting an item in the database", ex);
            }
            return null;
        };
        try {
            return executor.executeSelect(getConnection(), sql, id, getEntity);
        } catch (SQLException ex) {
            logger.error("Error finding element by ID: {}", id, ex);
            return Optional.empty();
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return getSessionManager().getCurrentSession().getConnection();
    }
}
