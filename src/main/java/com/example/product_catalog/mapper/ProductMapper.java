package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.ProductRequest;
import com.example.product_catalog.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequest dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }
}