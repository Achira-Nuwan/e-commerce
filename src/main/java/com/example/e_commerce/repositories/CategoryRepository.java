package com.example.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
