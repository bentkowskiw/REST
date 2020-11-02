package io.bentkowski.store.controller;

import io.bentkowski.store.entity.Product;
import io.bentkowski.store.entity.ShopOrder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ShopOrderDto implements Serializable {

    private LocalDateTime created;
    private Long id;
    private String buyersEmail;
    private List<ProductDto> listOfProducts;



    public ShopOrderDto(ShopOrder persistent) {
        this();
        this.created = persistent.getCreated() != null ? persistent.getCreated().toLocalDateTime() : null;
        this.id = persistent.getId();
        this.buyersEmail = persistent.getBuyersEmail();
        List<Product> ps = persistent.getProducts();
        if (ps != null) {
            Product[] products = new Product[ps.size()];
            ps.toArray(products);
            for (Product product : products) {
                this.listOfProducts.add(new ProductDto(product));
            }
        }


    }

    public ShopOrderDto() {
        this.listOfProducts = new LinkedList<>();
    }

    public ShopOrderDto(String buyersEmail, List<ProductDto> listOfProducts) {
        this();
        this.buyersEmail = buyersEmail;
        this.listOfProducts.addAll(listOfProducts);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public List<ProductDto> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProductDto> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrderDto that = (ShopOrderDto) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
