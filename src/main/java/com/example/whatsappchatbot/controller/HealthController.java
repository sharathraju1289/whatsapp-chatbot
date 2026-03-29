package com.example.whatsappchatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("{\"status\":\"UP\",\"message\":\"WhatsApp Chatbot API is running\"}");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }
    
}

