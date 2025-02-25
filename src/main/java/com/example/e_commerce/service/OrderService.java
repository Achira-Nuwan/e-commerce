package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Orders;

@Service
public interface OrderService {
    List<Orders> getAllOrders();

    Orders createOrder(Orders order);
}
