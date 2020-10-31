package io.bentkowski.store.model;

import java.util.function.Supplier;

public class NonExistentEntityException extends RuntimeException implements Supplier {
    @Override
    public Object get() {
        return this;
    }

    public NonExistentEntityException(Class cs, Object id) {
        super(String.format("Entity of class: %s and id: %s does not exist.",cs.getName(),id.toString()));
    }
}
