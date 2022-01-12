package com.example.project.service;

import android.content.Context;

import com.example.project.databinding.ActivityChatBinding;


public interface ChatServiceInterface {
    void sendMessage(Context applicationContext, ActivityChatBinding binding, String currentId, String senderId);
    void showMessages(String currentId,String chatId);
}
