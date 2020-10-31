package io.bentkowski.store.controller;

import io.bentkowski.store.model.NonExistentEntityException;
import io.bentkowski.store.model.Product;
import io.bentkowski.store.model.ProductRepository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class ProductRestController implements ProductRestApi {

    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, String SKU) {
        return productRepository.findById(SKU)
                .orElseThrow(new NonExistentEntityException(Product.class, SKU));

    }

    @Override
    public Iterable<Product> findProducts(String offset, String limit) {
        return null;
    }

    @Override
    public void deleteProduct(String SKU) {

    }
}
