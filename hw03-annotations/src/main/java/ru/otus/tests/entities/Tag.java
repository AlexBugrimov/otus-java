package ru.otus.tests.entities;

import lombok.*;

@Data
@RequiredArgsConstructor
public class Tag {

    @NonNull private final String value;

    private String attributeValue;

    public boolean isValid() {
        return value.matches("<([A-Za-z]*)\\b[^>]*>");
    }

    public String asString() {
        return value;
    }
}
