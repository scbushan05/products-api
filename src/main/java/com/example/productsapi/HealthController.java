package com.example.productsapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HealthController {

    @GetMapping
    public String health() {
        return "Application is Up!";
    }
}
