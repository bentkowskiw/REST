package io.bentkowski.store.controller;

import io.bentkowski.store.entity.Product;
import org.springframework.web.bind.annotation.*;


public interface ProductRestApi {


    ProductDto addProduct(ProductDto product);

    @PutMapping("/products/{SKU}")
    Product updateProduct(@RequestBody Product product, @PathVariable String SKU);


    Iterable<ProductDto> findProducts(String offset, @RequestParam(required = false) String limit);

    @DeleteMapping("/products/{SKU}")
    void deleteProduct(@PathVariable String SKU);


}
