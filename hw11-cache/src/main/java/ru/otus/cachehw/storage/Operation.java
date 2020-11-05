package ru.otus.cachehw.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ru.otus.core.model.BaseEntity;

@Getter
@AllArgsConstructor
public enum Operation {

    ADD("Saved entity"),
    REMOVE("Remove entity"),
    GET("Getting entity");

    private final String message;

    public <V extends BaseEntity> Result<V> execute(V content) {
        return new Result<>(this.getMessage(), content);
    }

    @Data
    public static class Result<T extends BaseEntity> {

        private final String message;
        private final T content;
    }
}