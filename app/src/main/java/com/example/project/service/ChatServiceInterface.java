package com.example.project.service;

import android.content.Context;

import com.example.project.databinding.ActivityChatBinding;
import com.example.project.entity.Chat;


public interface ChatServiceInterface {
    void sendMessage(Context applicationContext, ActivityChatBinding binding, String currentId, String senderId);
    void showMessages(String currentId, String chatId);
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading, ActivityChatBinding binding);
    Chat getMessage(ActivityChatBinding binding, String currentId, String senderId);
}
