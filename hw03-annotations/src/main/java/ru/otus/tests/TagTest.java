package ru.otus.tests;

import ru.otus.junit.Before;
import ru.otus.junit.Test;
import ru.otus.tests.entities.Tag;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTest {

    private Tag tag;

    @Before
    public void setUp() {
        tag = new Tag("<div>");
    }

    @Test
    public void tagAsStringTest() {
        assertThat(tag.asString())
                .isEqualTo("<div>");
    }

    @Test
    public void tagIsValidTest() {
        assertThat(tag.isValid())
                .isTrue();
    }

    @Test
    public void tagIsNotValidTest() {
        Tag invalidTag = new Tag("<span >>>");
        assertThat(invalidTag.isValid())
                .isFalse();
    }
}
