package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class EntitySQLMetaDataHandler implements EntitySQLMetaData {

    private static final String FIELD_MASK = "#FIELD";
    private final String tableName;
    private final String fieldNameId;
    private final List<String> fields;

    private EntitySQLMetaDataHandler(String tableName, String fieldNameId, List<String> fields) {
        this.tableName = tableName;
        this.fieldNameId = fieldNameId;
        this.fields = new LinkedList<>(fields);
    }

    public static EntitySQLMetaData of(EntityClassMetaData<?> metaData) {
        return new EntitySQLMetaDataHandler(
                metaData.getName(),
                metaData.getIdField().getName(),
                metaData.getFieldsWithoutId().stream()
                        .map(Field::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT * FROM %s", tableName);
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("%s WHERE %s = ?", getSelectAllSql(), fieldNameId);
    }

    @Override
    public String getInsertSql() {
        return String.format("""
                    INSERT INTO %s (%s)
                    VALUES (%s)
                """, tableName, String.join(", ", fields), getFieldsByTemplate(fields, "?")
        );
    }

    @Override
    public String getUpdateSql() {
        return String.format("""
                UPDATE %s
                SET %s
                WHERE %s = ?
                """, tableName, getFieldsByTemplate(fields, FIELD_MASK + " = ?"), fieldNameId
        );
    }

    private String getFieldsByTemplate(List<String> fields, String template) {
        return fields.stream()
                .map(field -> template.replace(FIELD_MASK, field))
                .collect(Collectors.joining(", "));
    }
}
