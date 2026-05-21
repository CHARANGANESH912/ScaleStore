package com.example.scalestore.service;

import com.example.scalestore.model.Product;
import com.example.scalestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        System.out.println("⚠️ Cache Miss! Fetching products directly from PostgreSQL Database...");
        return productRepository.findAll();
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(Product product) {
        System.out.println("🌱 Saving new product to database and evicting cache...");
        return productRepository.save(product);
    }

    /**
     * Executes product purchase under extreme concurrency.
     * Uses database locking to prevent race conditions during high-concurrency loads.
     */
    @Transactional
    @CacheEvict(value = "products", allEntries = true) // Automatically clear cache when stock changes
    public void purchaseProduct(Long id, int quantity) {
        // CRITICAL FIX: We must use a lock here to prevent race conditions
        Product product = productRepository.findByIdWithLock(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock available for product: " + product.getName());
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        System.out.println("🛒 Purchase successful for: " + product.getName() + " | Remaining Stock: " + product.getStock());
    }
}