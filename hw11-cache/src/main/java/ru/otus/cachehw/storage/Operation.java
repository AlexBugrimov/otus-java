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

    public <K, V extends BaseEntity> Result<K, V> execute(K key, V value) {
        return new Result<>(key, value, this.getActionDescription());
    }

    @Data
    public static class Result<K, V extends BaseEntity> {

        private final K key;
        private final V value;
        private final String action;
    }
}