package io.bentkowski.store.controller;

import io.bentkowski.store.model.Product;
import org.springframework.web.bind.annotation.*;


public interface ProductRestApi {

    @PostMapping("/products")
    Product addProduct(@RequestBody Product product) ;

    @PutMapping("/products/{SKU}")
    Product updateProduct(@RequestBody Product product, @PathVariable String SKU);

    @GetMapping("/products")
    Iterable<Product> findProducts(@RequestParam(required = false) String offset, @RequestParam(required = false) String limit);

    @DeleteMapping("/products/{SKU}")
    void deleteProduct(@PathVariable String SKU);


}
