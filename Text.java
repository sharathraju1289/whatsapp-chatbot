package com.example.whatsappchatbot.model;

import lombok.Data;

@Data
public class Text {
    private String body;
    
    public Text(String body) {
        this.body = body;
    }
}
