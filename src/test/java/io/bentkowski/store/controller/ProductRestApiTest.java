package io.bentkowski.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bentkowski.store.entity.Product;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestApiTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  ProductService productService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


     void setupProductsAndOrders()   throws Exception {
        int i =0;
        while (i<10)    {
            ProductDto product = new ProductDto("SKU-"+i, "NAME-"+i,(double)i/2);
            productService.save(product);
            i++;
        }

    }


    @AfterEach
    void deleteProductsAndOrders()  throws Exception    {
        Iterable<ProductDto> products = productService.findAll(null,null);
        products.forEach(product -> productService.deleteById(product.getSku()));
    }

    @Test
    void addProduct() throws Exception {

        //Should be OK
        Product product = new Product("Pink Sunglasses", "SKU-1", 89.99d);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());



    }

    @Test
    void updateProduct() throws Exception {
        Product product2 = new Product("Yellow Sunglasses", "SUMMER-001", 0.01d);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/products/" + product2.getSku())
                .content(asJsonString(product2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }


    @Test
    void findProducts() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/" )

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());



    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/products/"+"P-001" )

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
}