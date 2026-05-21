package com.example.scalestore.service;

import com.example.scalestore.model.Product;
import com.example.scalestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public Product purchaseProduct(Long id, int quantity) {
        Product product = getProductById(id);

        // Fixed to match the 'stock' variable naming
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock! Available: " + product.getStock());
        }

        // Fixed to update using getStock and setStock
        product.setStock(product.getStock() - quantity);
        return productRepository.save(product);
    }
}