package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WhatsAppResponse {
    @JsonProperty("messaging_product")
    private String messagingProduct = "whatsapp";
    
    private ResponseObject[] contacts;
    private ResponseObject[] messages;
    
    public WhatsAppResponse(ResponseObject[] contacts, ResponseObject[] messages) {
        this.contacts = contacts;
        this.messages = messages;
    }
    
    // Getters and Setters
    public String getMessagingProduct() { return messagingProduct; }
    public void setMessagingProduct(String messagingProduct) { this.messagingProduct = messagingProduct; }
    
    public ResponseObject[] getContacts() { return contacts; }
    public void setContacts(ResponseObject[] contacts) { this.contacts = contacts; }
    
    public ResponseObject[] getMessages() { return messages; }
    public void setMessages(ResponseObject[] messages) { this.messages = messages; }
}
