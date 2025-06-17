package com.example.product_catalog.service;

import com.example.product_catalog.model.Category;
import com.example.product_catalog.model.Product;
import com.example.product_catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository repo, CategoryService catService) {
        this.productRepository = repo;
        this.categoryService = catService;
    }

    public Product save(Product product, Long categoryId) {
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
    }
}