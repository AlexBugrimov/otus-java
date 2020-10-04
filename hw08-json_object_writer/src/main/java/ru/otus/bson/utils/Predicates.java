package ru.otus.bson.utils;

import java.util.Objects;
import java.util.function.Predicate;

public final class Predicates {

    public static Predicate<Object> isNull = Objects::isNull;

}
