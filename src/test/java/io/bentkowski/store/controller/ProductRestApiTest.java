package io.bentkowski.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bentkowski.store.model.Product;
import io.bentkowski.store.model.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addProduct() throws Exception {

        //Should be OK
        Product product = new Product("Pink Sunglasses", "SUMMER-001", 89.99d);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());


        //Duplicate unique key
        Product product2 = new Product("Yellow Sunglasses", "SUMMER-001", 0.01d);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(asJsonString(product2))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isConflict());


    }

    @Test
    void updateProduct() {
    }

    @Test
    void findProducts() throws Exception {
        Optional<Product> product = productRepository.findById("SUMMER-001");
        product.isEmpty();
    }

    @Test
    void deleteProduct() {
    }
}