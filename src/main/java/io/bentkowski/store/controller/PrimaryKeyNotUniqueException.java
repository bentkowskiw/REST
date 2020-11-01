package io.bentkowski.store.controller;

import java.util.function.Supplier;

public class PrimaryKeyNotUniqueException extends RuntimeException implements Supplier {
    @Override
    public Object get() {
        return this;
    }

    public PrimaryKeyNotUniqueException(Object id) {
        super(String.format("The given primary key: %s is not unique!", id.toString()));
    }
}
