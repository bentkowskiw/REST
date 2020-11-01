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

    public ProductDto save(ProductDto s) {

        if (!productRepository.findById(s.getSku()).isPresent()) {
            Product persistent = new Product(s);
            productRepository.saveAndFlush(persistent);
            return new ProductDto(persistent);
        } else
            throw new PrimaryKeyNotUniqueException(s.getSku());

    }

    public Iterable<ProductDto> findAll(Integer offset, Integer limit) {

        Iterable<Product> persitentCollection = productRepository.findAll();
        List<ProductDto> dtoCollection = new LinkedList<>();
        persitentCollection.forEach(product -> dtoCollection.add(new ProductDto(product)));
        return dtoCollection;
    }


    public Optional<Product> findById(String s) {
        return productRepository.findById(s);
    }

    public void deleteById(String s) {
        productRepository.deleteById(s);
    }


}
