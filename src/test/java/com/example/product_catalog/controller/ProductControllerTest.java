package com.example.product_catalog.controller;

import com.example.product_catalog.dto.ProductRequest;
import com.example.product_catalog.mapper.ProductMapper;
import com.example.product_catalog.model.Product;
import com.example.product_catalog.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct_success() throws Exception {
        ProductRequest dto = new ProductRequest();
        dto.setName("Laptop");
        dto.setPrice(3000000);
        dto.setCategoryId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productService.save(any(Product.class), eq(1L))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void testGetProductByCategory_success() throws Exception {
        Product product1 = new Product();
        product1.setName("TV");

        Product product2 = new Product();
        product2.setName("AC");

        when(productService.getByCategory(1L)).thenReturn(List.of(product1, product2));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetProductById_success() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Kulkas");

        when(productService.getById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kulkas"));
    }
}
