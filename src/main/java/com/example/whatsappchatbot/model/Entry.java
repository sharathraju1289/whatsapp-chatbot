package com.example.whatsappchatbot.model;

public class Entry {
    private String id;
    private Change[] changes;
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Change[] getChanges() { return changes; }
    public void setChanges(Change[] changes) { this.changes = changes; }
}
