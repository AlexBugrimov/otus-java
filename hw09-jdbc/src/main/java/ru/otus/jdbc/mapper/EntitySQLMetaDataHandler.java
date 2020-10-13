package ru.otus.jdbc.mapper;

import ru.otus.core.model.User;

public class EntitySQLMetaDataHandler implements EntitySQLMetaData {

    private final EntityClassMetaData<?> entityClassMetaData;

    private EntitySQLMetaDataHandler(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    public static EntitySQLMetaData of(EntityClassMetaData<?> entityClassMetaData) {
        return new EntitySQLMetaDataHandler(entityClassMetaData);
    }

    @Override
    public String getSelectAllSql() {
        return null;
    }

    @Override
    public String getSelectByIdSql() {
        return null;
    }

    @Override
    public String getInsertSql() {
        return null;
    }

    @Override
    public String getUpdateSql() {
        return null;
    }
}
