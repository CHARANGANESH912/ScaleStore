package com.example.scalestore.controller;

import com.example.scalestore.model.Product;
import com.example.scalestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // Fixed: Changed from saveProduct() to createProduct() to match your service layer
        productService.createProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }
}