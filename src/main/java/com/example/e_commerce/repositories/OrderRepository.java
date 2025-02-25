package com.example.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
