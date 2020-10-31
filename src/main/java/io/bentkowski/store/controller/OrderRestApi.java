package io.bentkowski.store.controller;

import io.bentkowski.store.model.Order;
import io.bentkowski.store.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderRestApi {


    @PostMapping("/orders")
    Product addOrder(@RequestBody Order order);

    @GetMapping("/orders")
    Iterable<Order> findOrdersInPeriod(@RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate, @RequestParam(required = false) String offset, @RequestParam(required = false) String limit);

}
