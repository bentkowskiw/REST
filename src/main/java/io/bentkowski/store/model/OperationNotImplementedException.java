package io.bentkowski.store.model;

public class OperationNotImplementedException extends RuntimeException{

    public OperationNotImplementedException(String operationName) {
        super(String.format("Operation: %s is not implemented",operationName));
    }

}
