package ru.otus.jdbc.mapper;

import ru.otus.core.model.User;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.DbExecutorImpl;

public class JdbcMapperMetaData implements JdbcMapper<User> {

    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperMetaData(EntitySQLMetaData entitySQLMetaData) {
        this.entitySQLMetaData = entitySQLMetaData;
    }

    public JdbcMapperMetaData(DbExecutor<User> dbExecutor, EntitySQLMetaData entitySQLMetaData) {

    }

    @Override
    public void insert(User objectData) {
        entitySQLMetaData.getInsertSql();
    }

    @Override
    public void update(User objectData) {
        entitySQLMetaData.getUpdateSql();
    }

    @Override
    public void insertOrUpdate(User objectData) {

    }

    @Override
    public User findById(Object id, Class<User> clazz) {
        entitySQLMetaData.getSelectByIdSql();
        return null;
    }
}
