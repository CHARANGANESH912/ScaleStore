package com.example.scalestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF since we are using tokens/Basic Auth for stateless API calls
                .csrf(csrf -> csrf.disable())

                // 2. Configure endpoint permissions
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to load the frontend website files completely free
                        .requestMatchers("/", "/index.html", "/static/**", "/favicon.ico").permitAll()

                        // Keep all your core API endpoints secured
                        .anyRequest().authenticated()
                )

                // 3. Keep your standard login enforcement active
                .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
    }
}