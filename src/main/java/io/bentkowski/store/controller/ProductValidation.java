package io.bentkowski.store.controller;

public interface ProductValidation {
    void validate(ProductDto productDto) throws ProductValidationError;
}
