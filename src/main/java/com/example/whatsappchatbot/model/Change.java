package com.example.whatsappchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Change {
    @JsonProperty("value")
    private Value value;
    
    public Value getValue() { return value; }
    public void setValue(Value value) { this.value = value; }
}
