package com.example.scalestore.service;

import com.example.scalestore.config.JwtUtils;
import com.example.scalestore.model.User;
import com.example.scalestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) user.setRole("ROLE_CUSTOMER");
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtils.generateToken(email);
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}