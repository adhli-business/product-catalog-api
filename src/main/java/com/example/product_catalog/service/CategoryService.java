package com.example.product_catalog.service;

import com.example.product_catalog.model.Category;
import com.example.product_catalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repo) {
        this.categoryRepository = repo;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));
    }
}
