package com.example.whatsappchatbot.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_logs")
public class MessageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String messageId;
    private String fromNumber;
    private String messageText;
    private String timestamp;
    private String responseText;
    private LocalDateTime loggedAt;
    
    // Constructors
    public MessageLog() {
        this.loggedAt = LocalDateTime.now();
    }
    
    public MessageLog(String messageId, String fromNumber, String messageText, String responseText) {
        this.messageId = messageId;
        this.fromNumber = fromNumber;
        this.messageText = messageText;
        this.responseText = responseText;
        this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        this.loggedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    
    public String getFromNumber() { return fromNumber; }
    public void setFromNumber(String fromNumber) { this.fromNumber = fromNumber; }
    
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getResponseText() { return responseText; }
    public void setResponseText(String responseText) { this.responseText = responseText; }
    
    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
