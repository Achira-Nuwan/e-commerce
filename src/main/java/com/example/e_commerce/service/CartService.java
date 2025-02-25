package com.example.e_commerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.e_commerce.dto.CartItemDto;
import com.example.e_commerce.dto.CartRequestDto;
import com.example.e_commerce.dto.CartResponseDto;
import com.example.e_commerce.dto.UpdateCartItemDto;
import com.example.e_commerce.entities.Cart;
import com.example.e_commerce.entities.CartItems;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.entities.User;
import com.example.e_commerce.repositories.CartItemsRepository;
import com.example.e_commerce.repositories.CartRepository;
import com.example.e_commerce.repositories.ProductRepository;
import com.example.e_commerce.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartResponseDto addToCart(CartRequestDto cartRequest) {
        if (cartRequest.getCartItems() == null || cartRequest.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart items cannot be null or empty");
        }
        // Find the user
        User user = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find or create the cart
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // Process cart items
        for (CartItemDto itemDTO : cartRequest.getCartItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            CartItems cartItem = new CartItems();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(itemDTO.getQuantity());
            cartItemRepository.save(cartItem);
        }

        return mapToResponse(cart);
    }

    public CartResponseDto getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return mapToResponse(cart);
    }

    // Convert CartItem to CartItemDto before passing to CartResponseDto
    @Transactional
    public CartResponseDto updateCartItem(UpdateCartItemDto updateCartItemDto) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(updateCartItemDto.getUserId());

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            // Find the cart item by product ID
            Optional<CartItems> cartItemOptional = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getProductId().equals(updateCartItemDto.getProductId()))
                    .findFirst();

            if (cartItemOptional.isPresent()) {
                CartItems cartItem = cartItemOptional.get();
                cartItem.setQuantity(updateCartItemDto.getQuantity());

                cartItemRepository.save(cartItem);
                cartRepository.save(cart);

                // Convert CartItem List to CartItemDto List
                List<CartItemDto> cartItemDtos = cart.getCartItems().stream()
                        .map(item -> new CartItemDto(item.getProduct().getProductId(), item.getQuantity()))
                        .collect(Collectors.toList());

                return new CartResponseDto(
                        cart.getId(),
                        cart.getUser().getId(),
                        cartItemDtos, // Pass converted DTO list
                        cart.getTotalPrice());
            } else {
                throw new RuntimeException("Product not found in cart");
            }
        } else {
            throw new RuntimeException("Cart not found for user");
        }
    }

    private CartResponseDto mapToResponse(Cart cart) {
        List<CartItemDto> items = cart.getCartItems().stream()
                .map(item -> new CartItemDto(item.getProduct().getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new CartResponseDto(cart.getId(), cart.getUser().getId(), items, totalPrice);
    }
}
