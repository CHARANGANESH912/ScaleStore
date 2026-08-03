package com.example.scalestore.controller;

import com.example.scalestore.security.CustomUserDetailsService;
import com.example.scalestore.security.JwtFilter;
import com.example.scalestore.security.JwtUtils;
import com.example.scalestore.model.Product;
import com.example.scalestore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldReturnProducts() throws Exception {

        Product product = Product.builder()
                .id(1L)
                .name("iPhone 16")
                .description("Apple Smartphone")
                .price(BigDecimal.valueOf(79999))
                .stock(15)
                .build();

        when(productService.getAllProducts())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("iPhone 16"))
                .andExpect(jsonPath("$[0].stock").value(15));
    }
}