package com.example.scalestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF protection for stateless API interactions
                .csrf(csrf -> csrf.disable())

                // 2. Grant unconditional public access to EVERY route (Kill 401 challenges)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        // NOTE: .httpBasic() is completely removed here to permanently kill the browser popup challenge.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}