package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories(); // Read all categories

    Category createCategory(Category category); // Create a category

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category); // Update a category

    void deleteCategory(Long id); // Remove a category
}
