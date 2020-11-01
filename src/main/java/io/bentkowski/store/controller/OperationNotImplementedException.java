package io.bentkowski.store.controller;

import java.util.function.Supplier;

public class OperationNotImplementedException extends RuntimeException implements Supplier {

    @Override
    public Object get() {
        return this;
    }

    public OperationNotImplementedException(String operationName) {
        super(String.format("Operation: %s is not implemented", operationName));
    }

}
