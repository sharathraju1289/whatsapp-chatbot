package com.example.whatsappchatbot.model;

import lombok.Data;

@Data
public class WhatsAppResponse {
    private String messaging_product;
    private String to;
    private ArrayOfMessage messages;
    
    public WhatsAppResponse(String to, String text) {
        this.messaging_product = "whatsapp";
        this.to = to;
        this.messages = new ArrayOfMessage(new Message(text));
    }
}
