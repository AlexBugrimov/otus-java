package ru.otus.bson.types;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.otus.bson.handlers.Predicates.isNull;

public class JsonObject implements javax.json.JsonObject {

    private final Map<String, JsonValue> values;

    public JsonObject(Map<String, JsonValue> values) {
        this.values = values;
    }

    @Override
    public JsonArray getJsonArray(String name) {
        return (JsonArray) values.get(name);
    }

    @Override
    public javax.json.JsonObject getJsonObject(String name) {
        return (javax.json.JsonObject) values.get(name);
    }

    @Override
    public JsonNumber getJsonNumber(String name) {
        return (JsonNumber) values.get(name);
    }

    @Override
    public JsonString getJsonString(String name) {
        return (JsonString) values.get(name);
    }

    @Override
    public String getString(String name) {
        return values.get(name).toString();
    }

    @Override
    public String getString(String name, String defaultValue) {
        return values.containsKey(name) ? values.get(name).toString() : defaultValue;
    }

    @Override
    public int getInt(String name) {
        return getJsonNumber(name).intValue();
    }

    @Override
    public int getInt(String name, int defaultValue) {
        return getInt(name);
    }

    @Override
    public boolean getBoolean(String name) {
        JsonValue value = this.get(name);
        if (isNull.test(value)) {
            throw new NullPointerException();
        } else if (value == JsonValue.TRUE) {
            return true;
        } else if (value == JsonValue.FALSE) {
            return false;
        } else {
            throw new ClassCastException();
        }
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        return getBoolean(name);
    }

    @Override
    public boolean isNull(String name) {
        return isNull.test(values.get(name));
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.containsValue(value);
    }

    @Override
    public JsonValue get(Object key) {
        return values.get(key);
    }

    @Override
    public JsonValue put(String key, JsonValue value) {
        return values.put(key, value);
    }

    @Override
    public JsonValue remove(Object key) {
        return values.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends JsonValue> map) {
        values.putAll(map);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public Set<String> keySet() {
        return values.keySet();
    }

    @Override
    public Collection<JsonValue> values() {
        return values.values();
    }

    @Override
    public Set<Entry<String, JsonValue>> entrySet() {
        return values.entrySet();
    }

    @Override
    public ValueType getValueType() {
        return ValueType.OBJECT;
    }

    @Override
    public String toString() {
        return values.entrySet().stream()
                .map(valueEntry -> String.format("\"%s\":%s", valueEntry.getKey(), valueEntry.getValue()))
                .collect(Collectors.joining(",", "{", "}"));
    }
}
