package com.example.scalestore.controller;

import com.example.scalestore.model.Product;
import com.example.scalestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }
}