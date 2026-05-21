package com.example.scalestore.controller;

import com.example.scalestore.model.Product;
import com.example.scalestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Public endpoint to fetch all items.
     * GET /api/products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Public high-concurrency endpoint to process purchases.
     * POST /api/products/purchase/{id}
     * Expects JSON Body: { "quantity": 1 }
     */
    @PostMapping("/purchase/{id}")
    public ResponseEntity<String> purchaseProduct(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> payload) {

        int quantity = payload.getOrDefault("quantity", 1);
        productService.purchaseProduct(id, quantity);
        return ResponseEntity.ok("Purchase successful! Stock updated cleanly.");
    }
}