package io.bentkowski.store.entity;

import io.bentkowski.store.controller.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity

@SQLDelete(sql = "UPDATE PRODUCTS SET deleted = 1 WHERE sku = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted <> 1")
public class Product implements Serializable {

    private String name;

    @Id
    private String sku;

    @CreationTimestamp
    private Timestamp created;

    private int deleted;

    private Double price;

    public Product() {
    }

    public Product(ProductDto dto) {
        this.sku = dto.getSku();
        this.name = dto.getName();
        this.price = dto.getPrice();
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
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

    @PreRemove
    public void deleteProduct() {
        this.deleted = 1;
    }


}
