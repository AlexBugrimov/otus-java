package ru.otus.bson;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.entities.Avatar;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BsonTest {

    @Test
    @DisplayName("Успешное преобразование объекта")
    public void successfulSerialization() {
        final Avatar avatar = new Avatar("image.png");
        final String json = new Bson().toJson(avatar);
        assertThat(json).isEqualTo("{\"image\":\"image.png\"}");
    }

    @Test
    @DisplayName("Успешное преобразование нулевого объекта")
    public void successfulSerializationNullObject() {
        final Avatar avatar = null;
        final String json = new Bson().toJson(avatar);
        assertThat(json).isEqualTo("null");
    }

    @Test
    @DisplayName("Успешное преобразование массива")
    public void successfulSerializationArray() {
        final int[] intArray = {1, 2, 3, 4, 5};
        final String json = new Bson().toJson(intArray);
        assertThat(json).isEqualTo("[1,2,3,4,5]");
    }

    @Test
    void test() {
        var gson = new Gson();
        var serializer = new Bson();
        assertEquals(gson.toJson(null), serializer.toJson(null));
        assertEquals(gson.toJson((byte)1), serializer.toJson((byte)1));
        assertEquals(gson.toJson((short)1f), serializer.toJson((short)1f));
        assertEquals(gson.toJson(1), serializer.toJson(1));
        assertEquals(gson.toJson(1L), serializer.toJson(1L));
        assertEquals(gson.toJson(1f), serializer.toJson(1f));
        assertEquals(gson.toJson(1d), serializer.toJson(1d));
        assertEquals(gson.toJson("aaa"), serializer.toJson("aaa"));
        assertEquals(gson.toJson('a'), serializer.toJson('a'));
        assertEquals(gson.toJson(new int[] {1, 2, 3}), serializer.toJson(new int[] {1, 2, 3}));
        assertEquals(gson.toJson(List.of(1, 2 ,3)), serializer.toJson(List.of(1, 2 ,3)));
        assertEquals(gson.toJson(Collections.singletonList(1)), serializer.toJson(Collections.singletonList(1)));
    }
}