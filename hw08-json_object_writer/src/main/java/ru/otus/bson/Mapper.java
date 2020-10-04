package ru.otus.bson;

import javax.json.JsonValue;

public interface Mapper {

    JsonValue toJsonValue(Object object);

}
