package io.bentkowski.store.controller;

import io.bentkowski.store.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
@Transactional
public class ShopOrderService {


    @Autowired
    private ShopOrderRepository shopOrderRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ShopOrderRepository getShopOrderRepository() {
        return shopOrderRepository;
    }

    public void setShopOrderRepository(ShopOrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public Iterable<ShopOrderDto> findOrdersInPeriod(
            LocalDateTime beginDate,
            LocalDateTime endDate,
            String offset,
            String limit
    ) {
        Integer intOffset, intLimit;
        intOffset = offset != null ? Integer.parseInt(offset) : null;
        intLimit = limit != null ? Integer.parseInt(limit) : null;


        Iterable<ShopOrder> persistentCollection = shopOrderRepository.findAll(ShopOrderSpecifications.getProductOrdersBetweenDates(beginDate, endDate));
        List<ShopOrderDto> dtoCollection = new LinkedList<>();
        persistentCollection.forEach(shopOrder -> dtoCollection.add(new ShopOrderDto(shopOrder)));
        return dtoCollection;

    }

    public ShopOrderDto addOrder(ShopOrderDto order) {
        validate(order);
        ShopOrder entity = new ShopOrder(order);
        List<ProductDto> products = order.getListOfProducts();
        List<Product> persistent = findAndValidate(products);
        entity.setProducts(persistent);
        shopOrderRepository.save(entity);
        order.setListOfProducts(products);
        return order;
    }

    private List<Product> findAndValidate(List<ProductDto> products) {
        List<Product> persistentProducts = new LinkedList<>();
        if (products != null) {

            String[] ids = new String[products.size()];
            int i = 0;
            for (ProductDto product : products) {
                ids[i] = product.getSku();
                i++;
            }
            Iterable<Product> iterable = productRepository.findAllById(Arrays.asList(ids));

            Iterator<Product> iterator = iterable.iterator();
            products = new LinkedList<>();

            while (iterator.hasNext()) {
                Product persistentProduct = iterator.next();
                persistentProducts.add(persistentProduct);
                products.add(new ProductDto(persistentProduct));
            }


        }
        return persistentProducts;

    }

    private void validate(ShopOrderDto shopOrderDto) {
        /*
        String email = productDto.getBuyersEmail();
        javax.mail.internet.InternetAddress emailAddr = new javax.mail.internet.InternetAddress(email);
        try {
            emailAddr.validate();
        }
        catch(javax.mail.internet.AddressException x)   {
            throw new ShopOrderValidationError(shopOrderDto,"byuersEmail",email);
        }
        */
    }
}
