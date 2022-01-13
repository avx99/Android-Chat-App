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
        String selector = "";
        if(stringCompare(currentId,chatId) > 0)
            selector = currentId + chatId;
        else
            selector = chatId + currentId;
        this.chatRepo.getMessages(ts, selector);
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
}
