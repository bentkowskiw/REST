package io.bentkowski.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;

    public ProductRestController() {

    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.save(product);
    }


    @PutMapping("/products/{SKU}")
    public ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable String SKU) {
        return productService.updateProduct(product, SKU);
    }


    @GetMapping("/products")
    public Iterable<ProductDto> findProducts(@RequestParam(required = false) String offset, @RequestParam(required = false) String limit) {
        return productService.findAll(offset, limit);
    }


    @DeleteMapping("/products/{sku}")
    public void deleteProduct(@PathVariable String sku) {
        productService.deleteById(sku);
    }
}
