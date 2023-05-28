package com.anusha.coffee;

public class ChatMessage {

    private String senderName;
    private String messageText;

    public ChatMessage(String senderName, String messageText) {
        this.senderName = senderName;
        this.messageText = messageText;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}

