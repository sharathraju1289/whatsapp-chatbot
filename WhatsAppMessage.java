package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WhatsAppMessage {
    @JsonProperty("from")
    private String from;
    
    @JsonProperty("text")
    private TextMessage text;
    
    @JsonProperty("message_id")
    private String messageId;
}
