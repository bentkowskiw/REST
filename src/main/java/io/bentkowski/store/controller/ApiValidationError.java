package io.bentkowski.store.controller;


import java.util.function.Supplier;

public class ApiValidationError extends RuntimeException implements Supplier {

    Object object;
    String field;
    Object rejected;
    String message;

    @Override
    public Object get() {
        return this;
    }


    public ApiValidationError(Object object, String field, Object rejected) {
        this.object = object;
        this.field = field;
        this.rejected = rejected;
        this.message = "Validation Error in class: %s. Field name: %s cannot be set to value: %s";
    }

    @Override
    public String toString() {
        return String.format(message, object.getClass(), field, rejected);
    }

}
