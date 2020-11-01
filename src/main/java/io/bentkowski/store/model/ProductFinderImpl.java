package io.bentkowski.store.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class ProductFinderImpl implements ProductFinder {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> findById(String s) {
       return Optional.of(entityManager.find(Product.class, s));
    }


    @Override
    public Iterable<Product> findAll(Integer offset, Integer limit) {
        return null;
    }

}
