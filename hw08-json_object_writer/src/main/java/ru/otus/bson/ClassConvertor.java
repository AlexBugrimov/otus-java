package ru.otus.bson;

import ru.otus.bson.handlers.Handler;
import ru.otus.bson.handlers.JsonHandler;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ClassConvertor implements Convertor {

    @Override
    public String convert(Object object) {
        return (object == null) ? toJson(JsonNull.VALUE) : toJson(object, object.getClass());
    }

    private String toJson(JsonNode node) {
        final Writer writer = new StringWriter();
        toJson(node, writer);
        return writer.toString();
    }

    private String toJson(Object object, Type type) {
        final Writer writer = new StringWriter();
        toJson(object, type, writer);
        return writer.toString();
    }

    private void toJson(JsonNode node, Writer writer) {
        final Handler jsonHandler = JsonHandler.from(writer);
        jsonHandler.write(node);
    }

    private void toJson(Object object, Type type, Writer writer) {

    }

    private String asString(Object object, Field field) {
//        dataFields.put(field, MetaInfo.from(object, field));
//        writer.toString(dataFields);
//        StringBuilder sb = new StringBuilder();
//        try {
//            field.setAccessible(true);
//            dataFields.put(field, new MetaInfo(object, field));
////            final Attributes fieldAttributes = metaInfo.getFiledAttributes();
//            final JsonType jsonPrimitives = new JsonPrimitives();
////            final boolean isPrimitive = jsonPrimitives.isTypeWith(fieldAttributes);
////            final Class<?> primitivesClass = jsonPrimitives.getClass(fieldAttributes.getDeclaredClass());
////            if (isPrimitive) {
////                sb.append('"').append(field.getName()).append('"').append(':').append(field.get(object));
////            }
//            final String result = '"' + field.getName() + '"' + ':' + field.get(object);
//            field.setAccessible(false);
//            return result;
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return "";
    }
}

/*
*         final boolean isInterface = field.getType().isInterface();
        final boolean isArray = field.getType().isArray();
        final boolean isPrimitive = field.getType().isPrimitive();
        final boolean isEnum = field.getType().isEnum();
        final boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        final boolean b = field.getType() instanceof Object;

//        final boolean isClass = Class.class.isAssignableFrom(field.getType());
* */
