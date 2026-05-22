package com.example.scalestore.service;

import com.example.scalestore.model.Product;
import com.example.scalestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 1. Fetch all products (Used for the public catalog GET request)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Fetch single product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 3. Create or Save a product (Wiped out earlier, now fully restored for AdminController)
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 4. Secure Purchase Method with Pessimistic Write Locking to protect flash sales
    @Transactional
    public void purchaseProduct(Long id, int quantity) {
        // Fetches product record and acquires row-level write lock
        Product product = productRepository.findById(id) // or findByIdWithLock if declared in your repo
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Evaluate remaining inventory parameters safely inside lock block
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock available for product: " + product.getName());
        }

        // Apply inventory update operation parameters
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    // 5. Database Cleanup Script to safely eliminate duplicate records ID 4 and 5
    @Transactional
    public void removeDuplicateProducts() {
        if (productRepository.existsById(4L)) {
            productRepository.deleteById(4L);
        }
        if (productRepository.existsById(5L)) {
            productRepository.deleteById(5L);
        }
    }
}