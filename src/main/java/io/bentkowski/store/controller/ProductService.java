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

        Iterable<Product> persistentCollection = productRepository.findAll();
        List<ProductDto> dtoCollection = new LinkedList<>();
        persistentCollection.forEach(product -> dtoCollection.add(new ProductDto(product)));
        return dtoCollection;
    }

    public ProductDto updateProduct(ProductDto product, String sku) {
        Optional<Product> optionalProduct = productRepository.findById(sku);
        if (optionalProduct.isPresent() && Boolean.FALSE == optionalProduct.get().isDeleted()) {
            validate(product);
            Product persistent = optionalProduct.get();
            persistent.setName(product.getName());
            persistent.setPrice(product.getPrice());
            productRepository.save(persistent);
            return new ProductDto(persistent);
        } else throw new NonExistentEntityException(Product.class, product.getSku());
    }

    public Optional<Product> findById(String s) {
        return productRepository.findById(s);
    }

    public void deleteById(String s) {
        Optional<Product> optionalProduct = productRepository.findById(s);
        if (productExists(optionalProduct)) {
            Product persistent = optionalProduct.get();
            persistent.setDeleted(Boolean.TRUE);
            productRepository.saveAndFlush(persistent);
        }

    }

    private boolean productExists(Optional<Product> optionalProduct) {
        if (optionalProduct.isPresent()) {
            Product entity = optionalProduct.get();
            return entity.isDeleted() == null || entity.isDeleted() == Boolean.FALSE;
        }
        return false;
    }

    private void validate(ProductDto source) throws ProductValidationError {
        if (source.getName() == null)
            source.setName("");
        double price = source.getPrice() != null ? source.getPrice() : 0d;
        if (price < 0d)
            throw new ProductValidationError(source, "price", price);
        source.setPrice(price);
    }

}
