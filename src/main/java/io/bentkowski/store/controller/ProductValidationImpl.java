package io.bentkowski.store.controller;

import org.springframework.stereotype.Component;

@Component
public class ProductValidationImpl implements ProductValidation {
    @Override
    public void validate(ProductDto source) throws ProductValidationError {

        if (source.getName() == null)
            source.setName("");
        double price = source.getPrice() != null ? source.getPrice() : 0d;
        if (price < 0d)
            throw new ProductValidationError(source, "price", price);

        source.setPrice(price);

    }
}
