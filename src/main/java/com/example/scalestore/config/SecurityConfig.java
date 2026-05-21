package com.example.scalestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the PasswordEncoder bean required by AuthController
     * for secure password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for REST APIs since we are using JWT/Stateless sessions
                .csrf(csrf -> csrf.disable())

                // Set up route permissions
                .authorizeHttpRequests(auth -> auth
                        // Allow everyone to access authentication and public products endpoints
                        .requestMatchers("/api/auth/**", "/api/products/**").permitAll()
                        // Require authentication for administrative actions
                        .requestMatchers("/api/admin/**").authenticated()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )

                // Keep the application completely stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}