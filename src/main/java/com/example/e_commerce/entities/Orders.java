package com.example.e_commerce.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1, initialValue = 10000)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime orderDateTime;
    private Double totalPrice;

    @PrePersist
    protected void onCreate() {
        this.orderDateTime = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "prodcutId"))
    private List<Product> orderProducts;

}
