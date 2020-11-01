package io.bentkowski.store.controller;

public class ProductValidationError extends ApiValidationError {
    ProductValidationError(Object source, String field, Object rejectedValue) {
        super(source, field, rejectedValue);
    }
}
