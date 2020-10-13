package ru.otus.jdbc.mapper;

import ru.otus.core.model.User;

public class JdbcMapperMetaData implements JdbcMapper<User> {

    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperMetaData(EntitySQLMetaData entitySQLMetaData) {
        this.entitySQLMetaData = entitySQLMetaData;
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
