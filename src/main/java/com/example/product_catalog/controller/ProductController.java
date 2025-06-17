// ProductController.java
package com.example.product_catalog.controller;

import com.example.product_catalog.dto.ProductRequest;
import com.example.product_catalog.mapper.ProductMapper;
import com.example.product_catalog.model.Product;
import com.example.product_catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;


    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/products")
    public Product create(@Valid @RequestBody ProductRequest dto) {
        Product product = mapper.toEntity(dto);
        return service.save(product, dto.getCategoryId());
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
