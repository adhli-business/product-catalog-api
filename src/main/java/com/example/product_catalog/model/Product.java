package com.example.product_catalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama produk wajib diisi")
    private String name;

    @Min(value = 1000, message = "Harga minimal Rp 1.000")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
