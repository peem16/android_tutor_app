package com.example.peem16.eakqlearning;

import java.util.Date;

public class Chat {

    private String messageText;
    private String messageUser;
    private String messageId;

    private long messageTime;


    public Chat(String messageText, String messageUser, String messageId) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageId = messageId;


        messageTime = new Date().getTime();
    }

    public Chat() {

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
