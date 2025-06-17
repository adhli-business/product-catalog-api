package com.example.product_catalog.service;

import com.example.product_catalog.model.Category;
import com.example.product_catalog.model.Product;
import com.example.product_catalog.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryService = mock(CategoryService.class);
        productService = new ProductService(productRepository, categoryService);
    }

    @Test
    void testSaveProductWithCategory() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        Product product = new Product();
        product.setName("Laptop");

        Product savedProduct = new Product();
        savedProduct.setId(10L);
        savedProduct.setName("Laptop");
        savedProduct.setCategory(category);

        when(categoryService.findById(1L)).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        Product result = productService.save(product, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals("Electronics", result.getCategory().getName());

        verify(categoryService, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetByCategory() {
        // Arrange
        List<Product> products = new ArrayList<>();
        Product p1 = new Product(); p1.setId(1L); p1.setName("Mouse");
        Product p2 = new Product(); p2.setId(2L); p2.setName("Keyboard");
        products.add(p1);
        products.add(p2);

        when(productRepository.findByCategoryId(5L)).thenReturn(products);

        // Act
        List<Product> result = productService.getByCategory(5L);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Mouse", result.get(0).getName());
        assertEquals("Keyboard", result.get(1).getName());
        verify(productRepository, times(1)).findByCategoryId(5L);
    }

    @Test
    void testGetByIdFound() {
        // Arrange
        Product product = new Product();
        product.setId(100L);
        product.setName("Monitor");

        when(productRepository.findById(100L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getById(100L);

        // Assert
        assertNotNull(result);
        assertEquals("Monitor", result.getName());
        verify(productRepository, times(1)).findById(100L);
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            productService.getById(999L);
        });

        assertEquals("Produk tidak ditemukan", ex.getMessage());
        verify(productRepository, times(1)).findById(999L);
    }
}
