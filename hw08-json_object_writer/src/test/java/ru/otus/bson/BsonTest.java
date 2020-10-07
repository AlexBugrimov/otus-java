package ru.otus.bson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.entities.Avatar;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(json).isEqualTo("{}");
    }

    @Test
    @DisplayName("Успешное преобразование массива")
    public void successfulSerializationArray() {
        final int[] intArray = {1, 2, 3, 4, 5};
        final String json = new Bson().toJson(intArray);
        assertThat(json).isEqualTo("[1,2,3,4,5]");
    }
}