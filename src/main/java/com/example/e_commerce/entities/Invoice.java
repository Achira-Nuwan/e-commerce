
package com.example.e_commerce.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_seq")

    @SequenceGenerator(name = "inv_seq", sequenceName = "invoice_sequence", allocationSize = 1, initialValue = 1000)
    private Long invNo;

    @Column(nullable = false)
    private String custName;

    private LocalDateTime createdAt;
    private Double totalPrice;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(name = "invoiced_products", joinColumns = @JoinColumn(name = "invoice_no"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> invoiceProducts;
}
