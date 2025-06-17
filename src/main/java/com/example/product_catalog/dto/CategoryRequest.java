package com.example.product_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Nama kategori wajib diisi")
    private String name;
}