package com.example.scalestore.controller;

import com.example.scalestore.model.Product;
import com.example.scalestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. GET Request: Public Catalog Browsing (Loads optimized data array from Upstash Redis Cache)
    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 2. POST Request: Secure Transaction Purchase (Invokes row-level Pessimistic Write Locking protection)
    @PostMapping("/api/products/purchase/{id}")
    public ResponseEntity<Map<String, String>> purchaseProduct(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> requestBody) {

        int quantity = requestBody.get("quantity");
        productService.purchaseProduct(id, quantity);

        return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "message", "Purchase order processed successfully!"
        ));
    }

}