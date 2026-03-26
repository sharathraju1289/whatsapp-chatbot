package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("from")
    private String from;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("timestamp")
    private String timestamp;
    
    @JsonProperty("text")
    private Text text;
    
    @JsonProperty("type")
    private String type;
    
    // Getters and Setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public Text getText() { return text; }
    public void setText(Text text) { this.text = text; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
