package com.example.scalestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {
        // Fetch product from DB
        Product product = productService.getProductById(id);

        // Pass data to HTML
        model.addAttribute("product", product);

        return "product_details";
    }
}