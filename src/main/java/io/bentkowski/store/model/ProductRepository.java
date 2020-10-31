package io.bentkowski.store.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository  implements ProductFinder, ProductSaver, ProductDeleter  {

    private ProductSaver productSaver;
    private ProductFinder productFinder;
    private ProductDeleter productDeleter;

    public ProductRepository(ProductSaver productSaver, ProductFinder productFinder, ProductDeleter productDeleter) {
        this.productSaver = productSaver;
        this.productFinder = productFinder;
        this.productDeleter = productDeleter;
    }


    public Product save(Product s) {
        return productSaver.save(s);
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
