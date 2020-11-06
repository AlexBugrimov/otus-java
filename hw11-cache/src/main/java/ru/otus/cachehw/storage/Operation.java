package ru.otus.cachehw.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ru.otus.core.model.BaseEntity;

@Getter
@AllArgsConstructor
public enum Operation {

    ADDITION("Save recording"),
    REMOVAL("Record deletion"),
    RECEIVING("Get record");

    private final String actionDescription;

    public <V extends BaseEntity> Result<V> execute(V value) {
        return new Result<>(this.getActionDescription(), value);
    }

    @Data
    public static class Result<V extends BaseEntity> {

        private final String action;
        private final V value;
    }
}