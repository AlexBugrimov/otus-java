package ru.otus.bson.info;

import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class ClassMetaInfo implements ClassInfo {

    private final List<FieldInfo> fields = new ArrayList<>();

    @Override
    public JsonValue asJsonValue() {
        return null;
    }

    public boolean addFieldInfo(FieldInfo fieldInfo) {
        return fields.add(fieldInfo);
    }
}
