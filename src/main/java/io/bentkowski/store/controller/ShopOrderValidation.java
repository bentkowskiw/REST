package io.bentkowski.store.controller;


public interface ShopOrderValidation {
    void validate(ShopOrderDto shopOrderDto) throws ShopOrderValidationError;
}
