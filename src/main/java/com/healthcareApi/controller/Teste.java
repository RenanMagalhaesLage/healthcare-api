package com.healthcareApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {
    @GetMapping("/api/hello")
    public String helloWorld() {
        return "Hello, Health API!";
    }
}
