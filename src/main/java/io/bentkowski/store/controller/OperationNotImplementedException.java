package io.bentkowski.store.controller;

public class OperationNotImplementedException extends RuntimeException{

    public OperationNotImplementedException(String operationName) {
        super(String.format("Operation: %s is not implemented",operationName));
    }

}
