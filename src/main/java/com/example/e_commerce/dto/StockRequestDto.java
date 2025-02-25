package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDto {
    private Long productId;
    private int qty;
}
