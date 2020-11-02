package io.bentkowski.store.controller;

public class ShopOrderValidationError extends ApiValidationError {
    ShopOrderValidationError(Object source, String field, Object rejectedValue) {
        super(source, field, rejectedValue);
    }
}
