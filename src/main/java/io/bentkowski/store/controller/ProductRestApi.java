package io.bentkowski.store.controller;

import org.springframework.web.bind.annotation.RequestParam;


public interface ProductRestApi {


    ProductDto addProduct(ProductDto product);


    ProductDto updateProduct(ProductDto product, String SKU);


    Iterable<ProductDto> findProducts(String offset, @RequestParam(required = false) String limit);


    void deleteProduct(String SKU);


}
