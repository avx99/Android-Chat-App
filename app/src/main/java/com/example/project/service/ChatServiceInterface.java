package com.example.project.service;

import com.example.project.databinding.ActivityChatBinding;
import com.example.project.entity.Chat;

public interface ChatServiceInterface {
    void sendMessage(Chat message);
    void showMessages();
    Chat getMessage(ActivityChatBinding binding);
}
