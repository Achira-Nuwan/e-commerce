package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Orders;
import com.example.e_commerce.repositories.OrderRepository;
import com.example.e_commerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

}
