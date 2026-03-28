package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {
    @JsonProperty("messages")
    private Message[] messages;
    
    @JsonProperty("contacts")
    private Contact[] contacts;
    
    @JsonProperty("metadata")
    private Metadata metadata;
    
    // Getters and Setters
    public Message[] getMessages() { return messages; }
    public void setMessages(Message[] messages) { this.messages = messages; }
    
    public Contact[] getContacts() { return contacts; }
    public void setContacts(Contact[] contacts) { this.contacts = contacts; }
    
    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata metadata) { this.metadata = metadata; }
}
