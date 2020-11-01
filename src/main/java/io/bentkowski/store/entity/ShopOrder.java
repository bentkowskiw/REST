package io.bentkowski.store.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class ShopOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String buyersEmail;

    @CreationTimestamp
    private Timestamp created;

    @ManyToMany
    private List<Product> products;

    public ShopOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyersEmail() {
        return buyersEmail;
    }

    public void setBuyersEmail(String buyersEmail) {
        this.buyersEmail = buyersEmail;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrder shopOrder = (ShopOrder) o;
        return getId().equals(shopOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
