package io.bentkowski.store.controller;

import io.bentkowski.store.entity.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductDto implements Serializable {
    private String sku;
    private String name;
    private LocalDateTime created;
    private Double price;

    public ProductDto(String sku, String name, LocalDateTime created, Double price) {
        this.sku = sku;
        this.name = name;
        this.created = created;
        this.price = price;
    }

    public ProductDto(Product entity) {
        this.sku = entity.getSku();
        this.name = entity.getName();
        this.created = entity.getCreated() == null ? null : entity.getCreated().toLocalDateTime();
        this.price = entity.getPrice();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
