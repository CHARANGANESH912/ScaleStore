package com.example.scalestore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> mockLogin(@RequestBody Map<String, String> credentials) {
        Map<String, String> response = new HashMap<>();
        // Fixed: Using .put() to assign the key-value pair correctly in Java Maps
        response.put("token", "mock-jwt-token-string-xyz123");

        return ResponseEntity.ok(response);
    }
}