package com.example.scalestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        // This line fetches all products from the DB and sends them to index.html
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
}