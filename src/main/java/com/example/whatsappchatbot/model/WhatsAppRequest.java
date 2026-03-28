package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class WhatsAppRequest {
    private Entry[] entry;

    public Entry[] getEntry() {
        return entry;
    }

    public void setEntry(Entry[] entry) {
        this.entry = entry;
    }
}
