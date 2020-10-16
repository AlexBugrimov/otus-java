package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.JdbcException;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Flow;

public class JdbcMapperMetaData implements JdbcMapper<User> {

    private final SessionManager sessionManager;
    private final DbExecutor<User> executor;
    private final EntityClassMetaData<User> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperMetaData(SessionManager sessionManager,
                              DbExecutor<User> executor,
                              EntityClassMetaData<User>  entityClassMetaData) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.entityClassMetaData = entityClassMetaData;
        this.entitySQLMetaData = EntitySQLMetaDataHandler.of(entityClassMetaData);
    }

    @Override
    public long insert(User user) {
        final String insertSql = entitySQLMetaData.getInsertSql();
        try {
            return executor.executeInsert(getConnection(), insertSql, entityClassMetaData.getValues(user));
        } catch (SQLException ex) {
            throw new JdbcException("Error adding a record", ex);
        }
    }

    @Override
    public void update(User user) {
        final String updateSql = entitySQLMetaData.getUpdateSql();
        try {
            executor.executeInsert(getConnection(), updateSql, entityClassMetaData.getValues(user));
        } catch (SQLException ex) {
            throw new JdbcException("Error updating a record", ex);
        }
    }

    @Override
    public void insertOrUpdate(User objectData) {

    }

    @Override
    public User findById(Object id, Class<User> clazz) {
        final String selectByIdSql = entitySQLMetaData.getSelectByIdSql();

        return null;
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return getSessionManager().getCurrentSession().getConnection();
    }
}
