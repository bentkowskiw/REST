package io.bentkowski.store.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceUnitTest {

    private final int MAX = 10;
    @Autowired
    private ProductService productService;

    @BeforeAll
    void setUp() {
        for (int i = 0; i < MAX; i++) {
            ProductDto p1 = new ProductDto("PRODUCT-" + i, "name-" + 1, (double) i * i);
            productService.save(p1);
        }

    }

    void clean()    {
        for (int i = 0; i < MAX; i++) {
            productService.deleteById("PRODUCT-" + i);
        }
    }


    @Test
    void save() {
        ProductDto p1 = new ProductDto("a", "name1", 1.0d);
        p1 = productService.save(p1);
        assertNotNull(p1);
        p1 = new ProductDto("b", "name2", 1.0d);
        p1 = productService.save(p1);
        assertNotNull(p1);

        final ProductDto p2 = new ProductDto("b", "name2", 1.0d);

        assertThrows(PrimaryKeyNotUniqueException.class, () ->
            productService.save(p2)
        );

        final ProductDto p3 = new ProductDto("r2d2", "name2", -1.0d);
        assertThrows(ProductValidationError.class, () -> productService.save(p3));

    }


    @Test
    void findAll() {

        Iterable<ProductDto> products = productService.findAll(null, null);
        int i = 0;
        Iterator<ProductDto> iterator = products.iterator();
        while (iterator.hasNext()) {
            ProductDto p = iterator.next();
            assertNotNull(p);
            i++;
        }


        assertEquals(MAX-1, i);


    }




    @Test
    void delete() {
        assertDoesNotThrow(() -> productService.deleteById("PRODUCT-9"));
        assertThrows(NonExistentEntityException.class, () -> productService.deleteById("PRODUCT-A99"));
    }
}