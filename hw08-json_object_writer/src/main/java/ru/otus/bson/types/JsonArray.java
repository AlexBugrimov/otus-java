package ru.otus.bson.types;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.util.*;
import java.util.stream.Collectors;

public class JsonArray implements javax.json.JsonArray {

    private final List<JsonValue> values = new ArrayList<>();

    @Override
    public JsonObject getJsonObject(int index) {
        return (JsonObject) get(index);
    }

    @Override
    public javax.json.JsonArray getJsonArray(int index) {
        return (JsonArray) get(index);
    }

    @Override
    public JsonNumber getJsonNumber(int index) {
        return (JsonNumber) get(index);
    }

    @Override
    public JsonString getJsonString(int index) {
        return (JsonString) get(index);
    }

    @Override
    public <T extends JsonValue> List<T> getValuesAs(Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(int index) {
        return getJsonString(index).getString();
    }

    @Override
    public String getString(int index, String defaultValue) {
        return getString(index);
    }

    @Override
    public int getInt(int index) {
        return getJsonNumber(index).intValue();
    }

    @Override
    public int getInt(int index, int defaultValue) {
        return getInt(index);
    }

    @Override
    public boolean getBoolean(int index) {
        final JsonValue value = get(index);
        if (value == null) {
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
    public boolean getBoolean(int index, boolean defaultValue) {
        return getBoolean(index);
    }

    @Override
    public boolean isNull(int index) {
        return get(index) == JsonValue.NULL;
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return values.contains(o);
    }

    @Override
    public Iterator<JsonValue> iterator() {
        return values.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return values.toArray(a);
    }

    @Override
    public boolean add(JsonValue jsonValue) {
        return values.add(jsonValue);
    }

    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return values.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JsonValue> c) {
        return values.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonValue> c) {
        return values.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return values.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return values.removeAll(c);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public JsonValue get(int index) {
        return values.get(index);
    }

    @Override
    public JsonValue set(int index, JsonValue element) {
        return null;
    }

    @Override
    public void add(int index, JsonValue element) {
        values.add(index, element);
    }

    @Override
    public JsonValue remove(int index) {
        return values.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return values.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return values.lastIndexOf(o);
    }

    @Override
    public ListIterator<JsonValue> listIterator() {
        return values.listIterator();
    }

    @Override
    public ListIterator<JsonValue> listIterator(int index) {
        return values.listIterator(index);
    }

    @Override
    public List<JsonValue> subList(int fromIndex, int toIndex) {
        return values.subList(fromIndex, toIndex);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.ARRAY;
    }

    @Override
    public String toString() {
        return values.stream()
                .map(JsonValue::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
