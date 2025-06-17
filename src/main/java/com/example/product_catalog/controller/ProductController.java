// ProductController.java
package com.example.product_catalog.controller;

import com.example.product_catalog.model.Product;
import com.example.product_catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public Product create(@Valid @RequestBody Product product, @RequestParam Long categoryId) {
        return service.save(product, categoryId);
    }

    @GetMapping("/categories/{id}/products")
    public List<Product> getByCategory(@PathVariable Long id) {
        return service.getByCategory(id);
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
