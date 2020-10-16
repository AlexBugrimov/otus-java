package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.JdbcException;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.handlers.ObjectHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcMapperMetaData<T> implements JdbcMapper<T> {

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
        this.entitySQLMetaData = EntitySQLMetaDataHandler.of(entityClassMetaData);
    }

    @Override
    public long insert(T entity) {
        final String sql = entitySQLMetaData.getInsertSql();
        try {
            return executor.executeInsert(getConnection(), sql, entityClassMetaData.getValues(entity));
        } catch (SQLException ex) {
            throw new JdbcException("Error while executing SQL request: " + sql, ex);
        }
    }

    @Override
    public void update(T entity) {
        final String sql = entitySQLMetaData.getUpdateSql();
        try {
            executor.executeInsert(getConnection(), sql, entityClassMetaData.getValues(entity));
        } catch (SQLException ex) {
            throw new JdbcException("Error while executing SQL request: " + sql, ex);
        }
    }

    @Override
    public void insertOrUpdate(T entity) {
        final T object = ObjectHandler.getEntity(entityClassMetaData, entity);
        var optionalEntity = findById(object);
        if (optionalEntity.isPresent()) {
            insert(entity);
        } else {
            update(entity);
        }
    }

    @Override
    public Optional<T> findById(Object id) {
        final String sql = entitySQLMetaData.getSelectByIdSql();
        try {
            return executor.executeSelect(getConnection(), sql, id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return ObjectHandler.build(resultSet, entityClassMetaData);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            });
        } catch (SQLException ex) {
            throw new JdbcException("Error while executing SQL request: " + sql, ex);
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
