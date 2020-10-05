package ru.otus.bson.info;

import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class ClassMetaInfo implements ClassInfo {

    private final List<FieldInfo> fields = new ArrayList<>();

    @Override
    public JsonValue asJsonValue() {
        fields.forEach(System.out::println);
        return null;
    }

    @Override
    public void addFieldInfo(FieldInfo fieldInfo) {
        fields.add(fieldInfo);
    }
}
