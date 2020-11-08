package io.bentkowski.store.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopOrderServiceUnitTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopOrderService shopOrderService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ShopOrderService getShopOrderService() {
        return shopOrderService;
    }

    public void setShopOrderService(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @BeforeAll
    void setUp() {
        for (int i = 0; i < 10; i++) {
            ProductDto productDto = new ProductDto("P-" + i, "Name=" + i, (double) i * 3.141592654);
            productService.save(productDto);
        }
        for (int j = 0; j < 5; j++) {
            List<ProductDto> products = new ArrayList<>();
            ProductDto productDto = new ProductDto();
            productDto.setSku("P-1");
            products.add(productDto);
            productDto = new ProductDto();
            productDto.setSku("P-2");
            products.add(productDto);
            ShopOrderDto shopOrderDto = new ShopOrderDto("bentkowski@bentkowski.io", products);
            shopOrderService.addOrder(shopOrderDto);
        }
    }

    @Test
    void findOrdersInPeriod() {
        LocalDateTime beginDate, endDate;
        beginDate = LocalDateTime.of(2000,1,1,0,0);
        endDate = LocalDateTime.of(2999,12,31,0,0);

        Iterable<ShopOrderDto> orders = shopOrderService.findOrdersInPeriod(beginDate, endDate, null, null);
        Iterator<ShopOrderDto> iterator = orders.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            ShopOrderDto order = iterator.next();
            assertNotNull(order);
            double amount = 0d;
            int j = 0;
            List<ProductDto> products = order.getListOfProducts();
            for (ProductDto product : products) {
                amount += product.getPrice();
                j++;
            }
            assertEquals(2, j);
            i++;
        }
        assertEquals(5, i);

    }

    @Test
    void addOrder() {
    }
}