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
                // 1. Disable CSRF since we use token-based authentication schemas
                .csrf(csrf -> csrf.disable())

                // 2. Configure endpoint asset pass-through mapping parameters
                .authorizeHttpRequests(auth -> auth
                        // Explicitly allow public users to view the static UI dashboard files
                        .requestMatchers("/", "/index.html", "/static/**", "/favicon.ico").permitAll()
                        .requestMatchers("/api/auth/**").permitAll() // Ensure login api requests pass through

                        // Secure all other functional service resources
                        .anyRequest().authenticated()
                )

                // 3. Enable basic authentication mapping protocols
                .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
    }

    // 4. Cryptographic Password Encoder Bean (Restored to fix AuthService crash)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}