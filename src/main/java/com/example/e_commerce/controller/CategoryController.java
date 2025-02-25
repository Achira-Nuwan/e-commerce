package com.example.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.entities.Category;
import com.example.e_commerce.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        Category createCategory = new Category();
        createCategory.setCatName(category.getCatName());

        Category createdCategory = categoryService.createCategory(createCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.ok(category);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        Category exisCategory = categoryService.getCategoryById(id);
        if (exisCategory == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            exisCategory.setCatName(category.getCatName()); // Update the existing category
            Category updatedCategory = categoryService.updateCategory(id, exisCategory); // Save the updated category
            return ResponseEntity.ok(updatedCategory);
        }
    }

    @DeleteMapping("/{id}")
    public void removeCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
