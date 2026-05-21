package com.example.scalestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // NOT @RestController
public class ViewController {
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}