package ru.otus.bson.info;

import javax.json.JsonValue;

public interface ClassInfo {

    void addFieldInfo(FieldInfo fieldInfo);

    JsonValue asJsonValue();
}
