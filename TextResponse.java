package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextResponse {
    @JsonProperty("body")
    private BodyResponse body;
    
    public TextResponse(String text) {
        this.body = new BodyResponse(text);
    }
    
    public BodyResponse getBody() { return body; }
    public void setBody(BodyResponse body) { this.body = body; }
}
