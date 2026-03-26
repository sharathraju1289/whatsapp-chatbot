package com.example.whatsappchatbot.model;

import lombok.Data;

@Data
public class Message {
    private Text text;
    
    public Message(String body) {
        this.text = new Text(body);
    }
}
