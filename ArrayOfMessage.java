package com.example.whatsappchatbot.model;

import lombok.Data;

@Data
public class ArrayOfMessage {
    private Message[] messages;
    
    public ArrayOfMessage(Message message) {
        this.messages = new Message[]{message};
    }
}
