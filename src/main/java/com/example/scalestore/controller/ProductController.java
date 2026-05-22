package com.example.scalestore.controller;

import com.example.scalestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // ... keep your existing GET and POST endpoints here ...

    @DeleteMapping("/api/products/cleanup-duplicates")
    public ResponseEntity<String> cleanupDuplicates() {
        // Routing the operation safely through the service layer
        productService.removeDuplicateProducts();
        return ResponseEntity.ok("Duplicate items ID 4 and 5 successfully wiped out from PostgreSQL database!");
    }
}