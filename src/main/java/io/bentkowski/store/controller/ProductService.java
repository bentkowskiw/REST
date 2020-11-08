package io.bentkowski.store.controller;

import io.bentkowski.store.entity.Product;
import io.bentkowski.store.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidation productValidation;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductValidation getProductValidation() {
        return productValidation;
    }

    public void setProductValidation(ProductValidation productValidation) {
        this.productValidation = productValidation;
    }

    public ProductDto save(ProductDto s) {


        if (!productRepository.existsById(s.getSku())) {
            productValidation.validate(s);
            Product persistent = new Product(s);
            productRepository.saveAndFlush(persistent);

            return new ProductDto(persistent);
        } else {
            throw new PrimaryKeyNotUniqueException(s.getSku());
        }


    }

    public Iterable<ProductDto> findAll(String offset, String limit) {
        Integer intOffset, intLimit;
        intOffset = offset != null ? Integer.parseInt(offset) : null;
        intLimit = limit != null ? Integer.parseInt(limit) : null;

        Iterable<Product> persistentCollection = productRepository.findAll();
        List<ProductDto> dtoCollection = new LinkedList<>();
        persistentCollection.forEach(product -> dtoCollection.add(new ProductDto(product)));
        return dtoCollection;
    }

    public ProductDto updateProduct(ProductDto product, String sku) {
        Optional<Product> optionalProduct = productRepository.findById(sku);
        if (optionalProduct.isPresent()) {
            productValidation.validate(product);
            Product persistent = optionalProduct.get();
            persistent.setName(product.getName());
            persistent.setPrice(product.getPrice());
            productRepository.save(persistent);
            return new ProductDto(persistent);
        } else throw new NonExistentEntityException(Product.class, product.getSku());
    }

    Optional<Product> findById(String s) {
        return productRepository.findById(s);
    }

    public void deleteById(String s) {
        boolean productExists = productRepository.existsById(s);
        if (productExists) {
            productRepository.deleteById(s);
        } else throw new NonExistentEntityException(Product.class, s);
    }




}
