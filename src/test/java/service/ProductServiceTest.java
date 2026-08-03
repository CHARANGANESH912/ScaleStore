package com.example.scalestore.service;

import com.example.scalestore.model.Product;
import com.example.scalestore.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.scalestore.exception.DuplicateResourceException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() {

        Product product = Product.builder()
                .id(1L)
                .name("iPhone 16")
                .description("Apple Smartphone")
                .price(BigDecimal.valueOf(79999))
                .stock(10)
                .build();

        when(productRepository.findAll())
                .thenReturn(List.of(product));

        List<Product> products =
                productService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("iPhone 16", products.get(0).getName());
    }
    @Test
    void shouldCreateProduct() {

        Product product = Product.builder()
                .name("Samsung Galaxy S25")
                .description("Samsung Flagship")
                .price(BigDecimal.valueOf(69999))
                .stock(20)
                .build();

        when(productRepository.existsByNameIgnoreCase(product.getName()))
                .thenReturn(false);

        when(productRepository.save(product))
                .thenReturn(product);

        Product savedProduct = productService.createProduct(product);

        assertEquals("Samsung Galaxy S25", savedProduct.getName());
        assertEquals(20, savedProduct.getStock());
    }
    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {

        Product product = Product.builder()
                .name("iPhone 16")
                .description("Apple Smartphone")
                .price(BigDecimal.valueOf(79999))
                .stock(10)
                .build();

        when(productRepository.existsByNameIgnoreCase(product.getName()))
                .thenReturn(true);

        assertThrows(
                DuplicateResourceException.class,
                () -> productService.createProduct(product)
        );
    }
}