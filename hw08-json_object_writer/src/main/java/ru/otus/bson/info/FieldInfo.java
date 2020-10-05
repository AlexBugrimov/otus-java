package ru.otus.bson.info;

public class FieldInfo {

    private final TypeField type;
    private final String name;
    private final Object value;

    public FieldInfo(TypeField type, String name, Object value) {
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
}
