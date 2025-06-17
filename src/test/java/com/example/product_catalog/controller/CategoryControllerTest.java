package com.example.product_catalog.controller;

import com.example.product_catalog.dto.CategoryRequest;
import com.example.product_catalog.model.Category;
import com.example.product_catalog.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryControllerTest {

    private CategoryService service;
    private CategoryController controller;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        service = mock(CategoryService.class);
        controller = new CategoryController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateCategory() throws Exception {
        // Arrange
        CategoryRequest request = new CategoryRequest();
        request.setName("Electronics");

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setName("Electronics");

        when(service.save(any(Category.class))).thenReturn(savedCategory);

        // Act & Assert
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Electronics"));

        // Verify interaction
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        verify(service).save(captor.capture());

        Category captured = captor.getValue();
        assertEquals("Electronics", captured.getName());
    }
}
