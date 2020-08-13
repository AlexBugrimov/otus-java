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
    public void tagAsStringTest() throws Exception {
        assertThat(tag.asString())
                .isEqualTo("<div>");
    }

    @Test
    public void tagIsValidTest() throws Exception {
        assertThat(tag.isValid())
                .isTrue();
    }

    @Test
    public void tagIsNotValidTest() throws Exception {
        Tag invalidTag = new Tag("<span >>>");
        assertThat(invalidTag.isValid())
                .isFalse();
    }
}
