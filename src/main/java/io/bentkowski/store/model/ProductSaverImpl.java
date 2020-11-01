package io.bentkowski.store.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ProductSaverImpl implements ProductSaver {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }


}
