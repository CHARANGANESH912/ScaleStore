package com.example.scalestore.controller;

import com.example.scalestore.model.User;
import com.example.scalestore.repository.UserRepository;
import com.example.scalestore.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Email is already registered!");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Encrypt using our production-ready BCrypt encoder bean
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        userRepository.save(user);

        // Auto-generate the JWT Token string for instant login convenience
        String token = jwtUtils.generateToken(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials!");
            return ResponseEntity.status(401).body(errorResponse);
        }

        String token = jwtUtils.generateToken(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}