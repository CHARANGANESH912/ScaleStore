package com.example.scalestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // This turns on the Redis/Caching logic you added to the Service
public class ScaleStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaleStoreApplication.class, args);
    }

}