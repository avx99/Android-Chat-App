package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;
import com.example.project.databinding.ActivityChatBinding;
import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.entity.User;
import com.example.project.service.ChatServiceInterface;
import com.example.project.service.impl.ChatService;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private ChatServiceInterface chatService = new ChatService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityChatBinding.inflate(getLayoutInflater());

        User chatUser = (User) getIntent().getSerializableExtra("chatUser");

        this.binding.textViewUserName.setText(chatUser.getName());

        setContentView(this.binding.getRoot());
    }
}