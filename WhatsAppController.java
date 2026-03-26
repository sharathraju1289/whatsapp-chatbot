package com.example.whatsappchatbot.controller;

import com.example.whatsappchatbot.entity.MessageLog;
import com.example.whatsappchatbot.model.*;
import com.example.whatsappchatbot.repository.MessageLogRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WhatsAppController {
    
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppController.class);
    
    @Autowired
    private MessageLogRepository messageLogRepository;
    
    @PostMapping
    public ResponseEntity<?> webhook(@RequestBody WhatsAppRequest request) {
        logger.info("Received webhook request: {}", request);
        
        try {
            // Parse the complex WhatsApp webhook structure
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.valueToTree(request);
            
            // Extract message from WhatsApp webhook structure
            String phoneNumber = extractPhoneNumber(rootNode);
            String messageText = extractMessageText(rootNode);
            
            if (phoneNumber != null && messageText != null) {
                // Log the incoming message
                MessageLog log = new MessageLog();
                log.setPhoneNumber(phoneNumber);
                log.setMessage(messageText);
                log.setResponse(""); // Will be set after processing
                messageLogRepository.save(log);
                
                logger.info("Incoming message from {}: {}", phoneNumber, messageText);
                
                // Generate response
                String responseText = generateResponse(messageText);
                log.setResponse(responseText);
                messageLogRepository.save(log);
                
                // Return WhatsApp response format
                WhatsAppResponse response = new WhatsAppResponse(phoneNumber, responseText);
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            logger.error("Error processing webhook", e);
        }
        
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
    
    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String verifyToken,
            @RequestParam("hub.challenge") String challenge) {
        
        String expectedToken = "your_verify_token_here"; // Change this in production
        if ("subscribe".equals(mode) && expectedToken.equals(verifyToken)) {
            logger.info("Webhook verified successfully");
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.badRequest().build();
    }
    
    private String extractPhoneNumber(JsonNode rootNode) {
        JsonNode changes = rootNode.path("entry").get(0).path("changes").get(0);
        JsonNode value = changes.path("value");
        return value.path("from").asText(null);
    }
    
    private String extractMessageText(JsonNode rootNode) {
        JsonNode changes = rootNode.path("entry").get(0).path("changes").get(0);
        JsonNode value = changes.path("value");
        return value.path("text").path("body").asText(null);
    }
    
    private String generateResponse(String message) {
        message = message.toLowerCase().trim();
        
        return switch (message) {
            case "hi", "hello", "hey" -> "Hello! 👋 How can I help you today?";
            case "bye", "goodbye", "see you" -> "Goodbye! 👋 Have a great day!";
            case "help" -> "Available commands:\n• hi - Say hello\n• bye - Say goodbye\n• help - Show this help";
            default -> "I received: '" + message + "' 🤖\nTry 'hi', 'bye', or 'help'";
        };
    }
}
