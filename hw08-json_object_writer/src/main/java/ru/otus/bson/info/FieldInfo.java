package ru.otus.bson.info;

import java.util.StringJoiner;

public class FieldInfo {

    private final TypeField type;
    private final String name;
    private final Object value;

    public static FieldInfo from(TypeField type, String name, Object value) {
        return new FieldInfo(type, name, value);
    }

    private FieldInfo(TypeField type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public TypeField getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FieldInfo.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("name='" + name + "'")
                .add("value=" + value)
                .toString();
    }
}
