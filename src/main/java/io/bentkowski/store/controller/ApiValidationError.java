package io.bentkowski.store.controller;


public class ApiValidationError extends RuntimeException {

    Object object;
    String field;
    Object rejected;
    String message;

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
