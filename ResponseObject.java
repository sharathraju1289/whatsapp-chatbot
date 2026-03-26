package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject {
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("input")
    private String input;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("to")
    private String to;
    
    @JsonProperty("type")
    private String messageType;
    
    @JsonProperty("text")
    private TextResponse text;
    
    // Constructors
    public ResponseObject() {}
    
    public ResponseObject(String type, String input) {
        this.type = type;
        this.input = input;
    }
    
    public ResponseObject(String id, String to, TextResponse text) {
        this.id = id;
        this.to = to;
        this.messageType = "text";
        this.text = text;
    }
    
    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    
    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }
    
    public TextResponse getText() { return text; }
    public void setText(TextResponse text) { this.text = text; }
}
