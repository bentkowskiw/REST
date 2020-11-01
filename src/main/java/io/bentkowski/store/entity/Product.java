package io.bentkowski.store.entity;

import io.bentkowski.store.controller.ProductDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    private String name;

    @Id
    private String sku;

    @CreationTimestamp
    private Timestamp created;

    private Boolean deleted;

    private Double price;

    public Product() {
    }

    public Product(ProductDto dto) {
        this.sku = dto.getSku();
        this.name = dto.getName() == null ? "" : dto.getName();
        this.price = dto.getPrice() == null ? 0d : dto.getPrice();
    }

    public Product(String name, String sku, Double price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String SKU) {
        this.sku = SKU;
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
        return sku.equals(product.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSku());
    }
}
