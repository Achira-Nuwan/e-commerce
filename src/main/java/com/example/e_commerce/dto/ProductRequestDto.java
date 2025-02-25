package com.example.e_commerce.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String productName;
    private String description;
    private String price;
    private String catId;
    private MultipartFile imageFile;
}
