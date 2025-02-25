package com.example.e_commerce.dto;

import com.example.e_commerce.entities.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

    public ProductResponseDto(Long productId, String productName, String description,
            Double price, Category category, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // ✅ Generate Getters & Setters
}
