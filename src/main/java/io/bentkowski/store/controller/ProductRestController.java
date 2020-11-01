package io.bentkowski.store.controller;

import io.bentkowski.store.model.NonExistentEntityException;
import io.bentkowski.store.model.Product;
import io.bentkowski.store.model.ProductRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController implements ProductRestApi {

    private final ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }


    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public Product updateProduct(Product product, String SKU) {
        Product persistent = productRepository.findById(SKU)
                .orElseThrow(new NonExistentEntityException(Product.class, SKU));
        persistent.setName(product.getName());
        persistent.setPrice(product.getPrice());
        return productRepository.save(persistent);
    }

    @Override
    public Iterable<Product> findProducts(String offset, String limit) {
        return null;
    }

    @Override
    public void deleteProduct(String SKU) {

    }
}
