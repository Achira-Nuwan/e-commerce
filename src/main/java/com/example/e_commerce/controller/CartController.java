package com.example.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.dto.CartRequestDto;
import com.example.e_commerce.dto.CartResponseDto;
import com.example.e_commerce.dto.UpdateCartItemDto;
import com.example.e_commerce.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartRequestDto cartRequest) {
        System.out.println("Received cart data:" + cartRequest);
        return ResponseEntity.ok(cartService.addToCart(cartRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<CartResponseDto> updateCart(@RequestBody UpdateCartItemDto dto) {
        CartResponseDto updatedCart = cartService.updateCartItem(dto);
        return ResponseEntity.ok(updatedCart);
    }
}
