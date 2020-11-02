package io.bentkowski.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ShopOrderRestController {

    @Autowired
    private ShopOrderService shopOrderService;

    public ShopOrderRestController() {
    }

    public ShopOrderService getShopOrderService() {
        return shopOrderService;
    }

    public void setShopOrderService(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @PostMapping("/orders")
    public ShopOrderDto addOrder(@RequestBody ShopOrderDto shopOrder) {
        return shopOrderService.addOrder(shopOrder);
    }

    @GetMapping("/orders")
    public Iterable<ShopOrderDto> findOrdersInPeriod(
            @RequestParam("beginDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime beginDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime endDate,
            @RequestParam(name = "offset", required = false) String offset,
            @RequestParam(name = "limit", required = false) String limit) {
        return shopOrderService.findOrdersInPeriod(beginDate, endDate, offset, limit);
    }
}
