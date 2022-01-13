package com.example.project.entity;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable, Comparable<Chat> {
    private String currentUserId;
    private String chatUserId;
    private String message;
    private Date messageDate;
    private String selector;

    public Chat(String currentUserId, String chatUserId, String message, Date messageDate) {
        this.currentUserId = currentUserId;
        this.chatUserId = chatUserId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public Chat(){

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

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public int stringCompare(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }

        else {
            return 0;
        }
    }

    @Override
    public int compareTo(Chat chat) {
        return getMessageDate().compareTo(chat.getMessageDate());
    }
}
