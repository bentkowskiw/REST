package io.bentkowski.store.controller;

import io.bentkowski.store.model.Product;
import io.bentkowski.store.model.ShopOrder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController implements OrderRestApi{

    @Override
    public Product addOrder(ShopOrder shopOrder) {
        return null;
    }

    @Override
    public Iterable<ShopOrder> findOrdersInPeriod(String beginDate, String endDate, String offset, String limit) {
        return null;
    }
}
