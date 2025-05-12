package com.example.e_commerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.dto.OrderProductsDto;
import com.example.e_commerce.dto.OrderRequestDto;
import com.example.e_commerce.entities.Orders;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.entities.User;
import com.example.e_commerce.service.OrderService;
import com.example.e_commerce.service.ProductService;
// import com.example.e_commerce.service.email.EmailService;
import com.example.e_commerce.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {

        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto dto) {

        List<OrderProductsDto> orderProducts = dto.getOrderProducts();

        if (orderProducts == null || orderProducts.isEmpty()) {
            return ResponseEntity.badRequest().body("Order products cannot be null or empty.");
        }

        double totalPrice = 0.0;

        List<Product> productList = new ArrayList<>();

        for (OrderProductsDto orderProduct : orderProducts) {
            Long productId = orderProduct.getProductId();
            int quantity = orderProduct.getQty();

            if (quantity <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be greater than 0 for productId:" + productId);
            }

            // Fetch items from database
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body("Invalid productId:" + productId);
            }

            // Add products to order
            productList.add(product);
            totalPrice += product.getPrice() * quantity;
        }

        User user = userService.getUserByEmail(dto.getUserEmail());

        Orders createOrder = new Orders();

        createOrder.setOrderProducts(productList);
        createOrder.setTotalPrice(totalPrice);
        createOrder.setUser(user);

        Orders createdOrder = orderService.createOrder(createOrder);

        return ResponseEntity.ok(createdOrder);
    }
}
