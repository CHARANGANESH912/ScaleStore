package com.example.scalestore.service;

import com.example.scalestore.exception.DuplicateResourceException;
import com.example.scalestore.exception.ResourceNotFoundException;
import com.example.scalestore.model.Product;
import com.example.scalestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Cacheable("products")
    public List<Product> getAllProducts() {

        System.out.println("Fetching products from DATABASE...");

        return productRepository.findAll();
    }

    @Cacheable(value = "product", key = "#id")
    public Product getProductById(Long id) {

        System.out.println("Fetching product " + id + " from DATABASE...");

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(Product product) {

        if (productRepository.existsByNameIgnoreCase(product.getName())) {
            throw new DuplicateResourceException(
                    "Product with name '" + product.getName() + "' already exists"
            );
        }

        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Product updateProduct(Product product) {

        if (!productRepository.existsById(product.getId())) {
            throw new ResourceNotFoundException("Product not found");
        }

        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }
}