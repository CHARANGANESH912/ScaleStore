package com.example.scalestore.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "orders") // "order" is a reserved word in SQL, so we use "orders"
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private Long productId; // Linking the purchase to the product
    private Integer quantity;
}
