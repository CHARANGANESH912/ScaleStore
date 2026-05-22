package com.example.scalestore.service;

import com.example.scalestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ... keep your existing methods like purchaseProduct here ...

    @Transactional
    public void removeDuplicateProducts() {
        // Safely check if they exist before deleting to avoid throwing EmptyResultDataAccessException
        if (productRepository.existsById(4L)) {
            productRepository.deleteById(4L);
        }
        if (productRepository.existsById(5L)) {
            productRepository.deleteById(5L);
        }
    }
}