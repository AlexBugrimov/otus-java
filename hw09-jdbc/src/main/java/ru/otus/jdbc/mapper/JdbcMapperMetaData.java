package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.JdbcException;
import ru.otus.core.model.User;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcMapperMetaData implements JdbcMapper<User> {

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<User> executor;
    private final EntityClassMetaData<User> classMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperMetaData(SessionManagerJdbc sessionManager,
                              DbExecutor<User> executor,
                              EntityClassMetaData<User> classMetaData) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.classMetaData = classMetaData;
        this.entitySQLMetaData = EntitySQLMetaDataHandler.of(classMetaData);
    }

    @Override
    public long insert(User user) {
        final String insertSql = entitySQLMetaData.getInsertSql();
        try {
            return executor.executeInsert(getConnection(), insertSql, classMetaData.getValues(user));
        } catch (SQLException ex) {
            throw new JdbcException("Error adding a record", ex);
        }
    }

    @Override
    public void update(User objectData) {
        final String updateSql = entitySQLMetaData.getUpdateSql();
    }

    @Override
    public void insertOrUpdate(User objectData) {

    }

    @Override
    public User findById(Object id, Class<User> clazz) {
        final String selectByIdSql = entitySQLMetaData.getSelectByIdSql();
        return null;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
