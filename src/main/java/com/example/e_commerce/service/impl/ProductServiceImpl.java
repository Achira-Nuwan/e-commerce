package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Product;
import com.example.e_commerce.repositories.ProductRepository;
import com.example.e_commerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product exisProduct = productRepository.findById(id).orElse(null);
        if (exisProduct == null) {
            return null;
        } else {
            exisProduct.setProductName(product.getProductName());
            exisProduct.setDescription(product.getDescription());
            exisProduct.setCategory(product.getCategory());
            return productRepository.save(exisProduct);
        }
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
