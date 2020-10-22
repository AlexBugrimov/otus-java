package ru.otus.jdbc.mapper;

import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.exceptions.JdbcException;
import ru.otus.jdbc.utils.ObjectHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import static ru.otus.jdbc.utils.ObjectHandler.build;

public final class JdbcMapperMetaData<T> implements JdbcMapper<T> {

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
            throw new JdbcException("Error adding entity", ex);
        }
    }

    @Override
    public void update(T entity) {
        final String sql = entitySQLMetaData.getUpdateSql();
        try {
            executor.executeInsert(getConnection(), sql, entityClassMetaData.getValues(entity));
        } catch (SQLException ex) {
            throw new JdbcException("Error updating entity", ex);
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
        try {
            final Function<ResultSet, T> rsHandler = resultSet -> build(resultSet, entityClassMetaData);
            return executor.executeSelect(getConnection(), sql, id, rsHandler);
        } catch (SQLException ex) {
            throw new JdbcException(String.format("Error finding element by ID: %s", id), ex);
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
