package com.example.project.entity;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {
    private String currentUserId;
    private String chatUserId;
    private String message;
    private Date messageDate;


    public Chat(String currentUserId, String chatUserId, String message, Date messageDate) {
        this.currentUserId = currentUserId;
        this.chatUserId = chatUserId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(String chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
