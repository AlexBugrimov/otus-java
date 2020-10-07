package ru.otus.bson.jsonTypes;

public class JsonString implements javax.json.JsonString {

    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public CharSequence getChars() {
        return value;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.STRING;
    }

    @Override
    public String toString() {
        return '"' + value + '"';
    }
}
