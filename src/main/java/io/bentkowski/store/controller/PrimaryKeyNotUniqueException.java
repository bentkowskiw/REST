package io.bentkowski.store.controller;

public class PrimaryKeyNotUniqueException extends RuntimeException{
    public PrimaryKeyNotUniqueException(Object id) {
        super(String.format("The given primary key: %s is not unique!",id.toString()));
    }
}
