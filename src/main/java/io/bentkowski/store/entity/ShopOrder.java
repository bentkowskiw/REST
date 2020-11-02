package io.bentkowski.store.entity;

import io.bentkowski.store.controller.ShopOrderDto;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@FilterDef(name = "dateBetween", parameters = {@ParamDef(name = "dateBegin", type = "timestamp"), @ParamDef(name = "dateEnd", type = "timestamp")})
@Filters({
        @Filter(name = "dateBetween", condition = ":dateBegin <= created and :dateEnd >= created"),
})
public class ShopOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String buyersEmail;

    @CreationTimestamp
    private Timestamp created;

    @ManyToMany
    private List<Product> products;

    public ShopOrder(ShopOrderDto order) {
        this.buyersEmail = order.getBuyersEmail();
        this.id = order.getId();
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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
