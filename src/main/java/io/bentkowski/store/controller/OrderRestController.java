package io.bentkowski.store.controller;

import io.bentkowski.store.model.Order;
import io.bentkowski.store.model.Product;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController implements OrderRestApi{

    @Override
    public Product addOrder(Order order) {
        return null;
    }

    @Override
    public Iterable<Order> findOrdersInPeriod(String beginDate, String endDate, String offset, String limit) {
        return null;
    }
}
