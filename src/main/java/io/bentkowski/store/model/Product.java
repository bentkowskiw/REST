package io.bentkowski.store.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Product {

    private String name;

    @Id
    private String SKU;

    @CreationTimestamp
    private Timestamp created;

    private Boolean deleted;

    private Double price;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp dateCreated) {
        this.created = dateCreated;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return SKU.equals(product.getSKU());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSKU());
    }
}
