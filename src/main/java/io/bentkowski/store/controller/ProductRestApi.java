package io.bentkowski.store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


public interface ProductRestApi {


    ProductDto addProduct(ProductDto product);


    ProductDto updateProduct(ProductDto product, String SKU);


    Iterable<ProductDto> findProducts(String offset, @RequestParam(required = false) String limit);

    @DeleteMapping("/products/{SKU}")
    void deleteProduct(@PathVariable String SKU);


}
