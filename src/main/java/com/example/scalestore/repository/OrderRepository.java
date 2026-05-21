package com.example.scalestore.repository;

import com.example.scalestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * We use an Interface because Spring Data JPA will provide
 * the implementation at runtime.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // You get .save(), .findAll(), and .findById() for free!
}
