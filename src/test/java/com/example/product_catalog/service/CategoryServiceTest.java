package com.example.product_catalog.service;

import com.example.product_catalog.model.Category;
import com.example.product_catalog.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {

    private CategoryRepository repository;
    private CategoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        service = new CategoryService(repository);
    }

    @Test
    void testSaveCategory() {
        // Arrange
        Category input = new Category();
        input.setName("Books");

        Category saved = new Category();
        saved.setId(1L);
        saved.setName("Books");

        when(repository.save(input)).thenReturn(saved);

        // Act
        Category result = service.save(input);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Books", result.getName());
        verify(repository, times(1)).save(input);
    }

    @Test
    void testFindById_Found() {
        // Arrange
        Category category = new Category();
        category.setId(2L);
        category.setName("Toys");

        when(repository.findById(2L)).thenReturn(Optional.of(category));

        // Act
        Category result = service.findById(2L);

        // Assert
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Toys", result.getName());
        verify(repository, times(1)).findById(2L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.findById(99L);
        });

        assertEquals("Kategori tidak ditemukan", exception.getMessage());
        verify(repository, times(1)).findById(99L);
    }
}
