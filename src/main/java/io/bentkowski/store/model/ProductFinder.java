package io.bentkowski.store.model;

import java.util.Optional;

public interface ProductFinder {


    Optional<Product> findById(String s);
    Iterable<Product> findAll(Integer offset, Integer limit);
}
