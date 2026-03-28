package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BodyResponse {
    @JsonProperty("text")
    private String text;
    
    public BodyResponse(String text) {
        this.text = text;
    }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
