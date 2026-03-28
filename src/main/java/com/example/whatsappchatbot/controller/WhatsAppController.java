package com.example.whatsappchatbot.controller;

import com.example.whatsappchatbot.entity.MessageLog;
import com.example.whatsappchatbot.model.*;
import com.example.whatsappchatbot.repository.MessageLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WhatsAppController {
    
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppController.class);
    
@Autowired(required = false)
    private MessageLogRepository messageLogRepository;

    @Value("${whatsapp.verify.token:myfallbacktoken}")
    private String expectedToken;
    
    @PostMapping
    public ResponseEntity<WhatsAppResponse> handleWebhook(@RequestBody WhatsAppRequest request) {
        logger.info("Received webhook request: {}", request);
        
        try {
            // Extract message from WhatsApp webhook
            Message message = extractMessage(request);
            
            if (message != null && "text".equals(message.getType()) && message.getText() != null) {
                String userMessage = message.getText().getBody().getText().toLowerCase().trim();
                String userPhone = message.getFrom();
                String messageId = message.getId();
                
                logger.info("Processing message from {}: {}", userPhone, userMessage);
                
                // Generate response
                String responseText = generateResponse(userMessage);
                
                // Log the message if DB available
                MessageLog log = new MessageLog(messageId, userPhone, userMessage, responseText);
                if (messageLogRepository != null) {
                    messageLogRepository.save(log);
                    logger.info("Logged message and sending response: {}", responseText);
                } else {
                    logger.warn("No database available, skipping log save for message: {}", messageId);
                }
                
                // Create WhatsApp response
                ResponseObject[] contacts = {new ResponseObject("delivery", userPhone)};
                ResponseObject[] messages = {
                    new ResponseObject(messageId, userPhone, new TextResponse(responseText))
                };
                
                WhatsAppResponse response = new WhatsAppResponse(contacts, messages);
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            logger.error("Error processing webhook: {}", e.getMessage(), e);
        }
        
        WhatsAppResponse errorResponse = new WhatsAppResponse(new ResponseObject[0], new ResponseObject[0]);
        return ResponseEntity.ok(errorResponse);
    }
    
    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.challenge") String challenge,
            @RequestParam("hub.verify_token") String verifyToken) {
        
        if ("subscribe".equals(mode) && expectedToken.equals(verifyToken)) {
            logger.info("Webhook verified successfully");
            return ResponseEntity.ok(challenge);
        }
        
        logger.warn("Webhook verification failed");
        return ResponseEntity.badRequest().build();
    }
    
    private Message extractMessage(WhatsAppRequest request) {
        if (request != null && request.getEntry() != null && request.getEntry().length > 0) {
            Entry entry = request.getEntry()[0];
            if (entry.getChanges() != null && entry.getChanges().length > 0) {
                Change change = entry.getChanges()[0];
                if (change.getValue() != null && change.getValue().getMessages() != null 
                    && change.getValue().getMessages().length > 0) {
                    return change.getValue().getMessages()[0];
                }
            }
        }
        return null;
    }
    
    private String generateResponse(String userMessage) {
        switch (userMessage.toLowerCase()) {
            case "hi":
            case "hello":
            case "hey":
                return "Hello! 👋 How can I help you today?";
            case "bye":
            case "goodbye":
            case "see you":
                return "Goodbye! 👋 Have a great day!";
            case "help":
                return "I can respond to: hi/hello, bye/goodbye, help";
            default:
                return "I received: '" + userMessage + "'. Try saying 'hi' or 'bye'! 😊";
        }
    }
}
