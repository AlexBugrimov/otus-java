package ru.otus.bson.info;

import ru.otus.bson.types.JsonObjectBuilder;

import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class ClassMetaInfo implements ClassInfo {

    private final List<FieldInfo> fields = new ArrayList<>();

    @Override
    public JsonValue asJsonValue() {
        final JsonObjectBuilder builder = new JsonObjectBuilder();
        for (FieldInfo fieldInfo : fields) {
            builder.add(fieldInfo.getName(), fieldInfo.getType().getJsonValue(fieldInfo.getValue()));
        }
        return builder.build();
    }

    @Override
    public void addFieldInfo(FieldInfo fieldInfo) {
        fields.add(fieldInfo);
    }
}
