package com.example.scalestore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> mockLogin(@RequestBody Map<String, String> credentials) {
        // Returns a dummy token to satisfy the frontend's text parsing logic
        return ResponseEntity.ok("mock-jwt-token-string");
    }
}