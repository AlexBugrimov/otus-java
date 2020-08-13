package ru.otus.tests.entities;

public class Tag {

    private final String value;
    private String attributeValue;

    public Tag(String value) {
        this.value = value;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attribute) {
        this.attributeValue = attribute;
    }

    public boolean isValid() {
        return value.matches("<([A-Za-z]*)\\b[^>]*>");
    }

    public String asString() {
        return value;
    }
}
