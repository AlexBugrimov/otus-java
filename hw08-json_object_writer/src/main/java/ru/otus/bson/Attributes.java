package ru.otus.bson;

import java.lang.reflect.Type;

public interface Attributes {

    String getName();

    Type getType();

    Class<?> getDeclaredClass();
}
