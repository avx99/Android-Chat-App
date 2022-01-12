package com.example.project.service.impl;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.project.databinding.ActivityChatBinding;
import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.entity.Chat;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.repository.ChatRepo;
import com.example.project.service.ChatServiceInterface;


import java.util.Date;

public class ChatService implements ChatServiceInterface {
    private ChatRepo chatRepo = new ChatRepo();
    private OnTransactionListReceivedListener ts;


    public ChatService(OnTransactionListReceivedListener ts){
        this.ts = ts;
    }

    @Override
    public void sendMessage(Context applicationContext,ActivityChatBinding binding, String currentId, String senderId) {
        Chat message = this.getMessage(binding,currentId,senderId);
        this.chatRepo.createMessage(message,applicationContext,binding);
    }

    @Override
    public void showMessages(String currentId, String chatId) {
        this.chatRepo.getMessages(ts, currentId, chatId);
    }
    @Override
    public Chat getMessage(ActivityChatBinding binding, String currentId,String senderId) {
        String message = binding.message.getText().toString();
        return new Chat(currentId,senderId,message,new Date());
    }

    @Override
    public void loading(Boolean isLoading, ActivityChatBinding binding) {
        if (isLoading){
            binding.progressBarChatConversation.setVisibility(View.VISIBLE);
        }
        else{
            binding.progressBarChatConversation.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showToast(Context applicationContext, String message) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show();
    }
}
