package com.example.product_catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank(message = "Nama produk wajib diisi")
    private String name;

    @Min(value = 1000, message = "Harga minimal Rp 1.000")
    private Integer price;

    private Long categoryId;
}