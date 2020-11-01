package io.bentkowski.store.model;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository implements ProductFinder, ProductSaver, ProductDeleter {

    private ProductSaver productSaver;
    private ProductFinder productFinder;
    private ProductDeleter productDeleter;

    public ProductSaver getProductSaver() {
        return productSaver;
    }

    public void setProductSaver(ProductSaver productSaver) {
        this.productSaver = productSaver;
    }

    public ProductFinder getProductFinder() {
        return productFinder;
    }

    public void setProductFinder(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public ProductDeleter getProductDeleter() {
        return productDeleter;
    }

    public void setProductDeleter(ProductDeleter productDeleter) {
        this.productDeleter = productDeleter;
    }

    public Product save(Product s) {

        if (!productFinder.findById(s.getSku()).isPresent())
            return productSaver.save(s);
        else
            throw new PrimaryKeyNotUniqueException(s.getSku());

    }

    public Iterable<Product> findAll(Integer offset, Integer limit) {
        return productFinder.findAll(offset, limit);
    }

    @Override
    public Optional<Product> findById(String s) {
        return productFinder.findById(s);
    }

    public void deleteById(String s) {
        productDeleter.deleteById(s);
    }


}
