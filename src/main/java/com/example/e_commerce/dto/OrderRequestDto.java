package com.example.e_commerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private List<OrderProductsDto> orderProducts;
}
