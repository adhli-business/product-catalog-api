package com.example.product_catalog.controller;

import com.example.product_catalog.dto.CategoryRequest;
import com.example.product_catalog.model.Category;
import com.example.product_catalog.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(@Valid @RequestBody CategoryRequest dto) {
    Category category = new Category();
    category.setName(dto.getName());
    return service.save(category);
}
}
