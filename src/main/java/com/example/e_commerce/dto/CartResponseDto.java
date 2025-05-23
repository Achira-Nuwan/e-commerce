package com.example.e_commerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Long userId;
    private List<CartItemDto> items;
    private double totalPrice;
}
