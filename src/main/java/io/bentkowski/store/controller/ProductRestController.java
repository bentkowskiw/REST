package io.bentkowski.store.controller;

import io.bentkowski.store.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductRestController implements ProductRestApi {

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

    @Override
    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @Override

    public Product updateProduct(Product product, String SKU) {
        /*
        Product persistent = productService.findById(SKU)
                .orElseThrow(new NonExistentEntityException(Product.class, SKU));
        persistent.setName(product.getName());
        persistent.setPrice(product.getPrice());
        return productService.save(persistent);
        */

        return null;
    }

    @Override
    @GetMapping("/products")
    public Iterable<ProductDto> findProducts(@RequestParam(required = false) String offset, @RequestParam(required = false) String limit) {
        Integer intOffset, intLimit;
        intOffset = offset != null ? Integer.parseInt(offset) : null;
        intLimit = limit != null ? Integer.parseInt(limit) : null;

        return productService.findAll(intOffset, intLimit);
    }

    @Override
    public void deleteProduct(String SKU) {

    }
}
