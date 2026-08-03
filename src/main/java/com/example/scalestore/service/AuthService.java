package com.example.scalestore.service;

import com.example.scalestore.dto.auth.AuthResponse;
import com.example.scalestore.dto.auth.LoginRequest;
import com.example.scalestore.dto.auth.RegisterRequest;
import com.example.scalestore.exception.DuplicateResourceException;
import com.example.scalestore.security.CustomUserDetails;
import com.example.scalestore.security.JwtUtils;
import com.example.scalestore.model.Role;
import com.example.scalestore.model.User;
import com.example.scalestore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        UserDetails userDetails = new CustomUserDetails(user);

        String jwt = jwtUtils.generateToken(userDetails);

        return new AuthResponse(jwt);
    }
}