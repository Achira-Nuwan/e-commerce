package com.example.e_commerce.controller;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.e_commerce.dto.ProductRequestDto;
import com.example.e_commerce.dto.ProductResponseDto;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.service.CategoryService;
import com.example.e_commerce.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();

            List<ProductResponseDto> responseDtos = products.stream().map(product -> {
                String imageUrl = null;
                if (product.getImage() != null) {
                    imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(product.getImage());
                }

                return new ProductResponseDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory(),
                        // imageUrl
                        product.getImageUrl());
            }).collect(Collectors.toList());

            return ResponseEntity.ok(responseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@ModelAttribute ProductRequestDto dto) {

        Long categoryId = Long.valueOf(dto.getCatId());
        Double productPrice = Double.valueOf(dto.getPrice());

        try {

            Category category = categoryService.getCategoryById(categoryId);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
            }

            byte[] imageData = null;
            if (!dto.getImageFile().isEmpty()) {
                imageData = dto.getImageFile().getBytes();
            }

            // Category createCategory = categoryService.getCategoryById(categoryId);

            Product createProduct = new Product();
            createProduct.setProductName(dto.getProductName());
            createProduct.setDescription(dto.getDescription());
            createProduct.setPrice(productPrice);
            createProduct.setCategory(category);
            createProduct.setImage(imageData);

            Product createdProduct = productService.createProduct(createProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating product:" + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Found.");
            } else {
                return ResponseEntity.ok(product);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching product:" + e.getMessage());
        }
    }

    /*
     * @GetMapping("/{id}/image")
     * public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
     * try {
     * Product product = productService.getProductById(id);
     * if (product == null || product.getImage() == null) {
     * return ResponseEntity.notFound().build();
     * }
     * 
     * return ResponseEntity.ok()
     * .header("Content-Type", "image/jpeg")
     * .body(product.getImage());
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     * }
     * }
     */

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable Long id,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("catId") Long catId,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try {
            Product exisProduct = productService.getProductById(id);
            if (exisProduct == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product Not Found.");
            } else {
                exisProduct.setProductName(productName);
                exisProduct.setDescription(description);
                exisProduct.setPrice(price);

                Category category = categoryService.getCategoryById(catId);
                exisProduct.setCategory(category);

                // only update image if provided
                if (imageFile != null && !imageFile.isEmpty()) {
                    exisProduct.setImage(imageFile.getBytes());
                }

                Product updatedProduct = productService.updateProduct(id, exisProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating product:" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
