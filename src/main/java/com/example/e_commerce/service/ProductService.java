package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteById(Long id);
}
