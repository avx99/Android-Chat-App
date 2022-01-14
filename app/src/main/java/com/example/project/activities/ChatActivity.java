package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project.R;
import com.example.project.adapter.ChatAdapter;
import com.example.project.databinding.ActivityChatBinding;
import com.example.project.entity.Chat;
import com.example.project.entity.User;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.service.ChatServiceInterface;
import com.example.project.service.impl.ChatService;
import com.example.project.utils.CONST;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ChatActivity extends AppCompatActivity implements OnTransactionListReceivedListener {
    private ActivityChatBinding binding;
    private ChatServiceInterface chatService = new ChatService(this);
    private User chatUser;
    private String currentId;
    private SharedPreferences sharedpreferences;
    private ArrayList<Chat> messages = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityChatBinding.inflate(getLayoutInflater());

        SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        currentId = sharedpreferences.getString(CONST.KEY_USER_ID,"");
        this.chatUser = (User) getIntent().getSerializableExtra("chatUser");

        chatService.loading(true,binding);
        this.chatService.showMessages(currentId,chatUser.getId());
        this.binding.textViewUserName.setText(chatUser.getName());






        setContentView(this.binding.getRoot());

        setListeners();
    }

    public void setListeners(){
        this.binding.buttonSendMessage.setOnClickListener(e -> {
            this.chatService.sendMessage(getApplicationContext(),binding,currentId,chatUser.getId());
            this.binding.message.setText("");
            this.messages.clear();
            this.chatService.showMessages(currentId,chatUser.getId());
//            chatAdapter.notifyDataSetChanged();

        });
        this.binding.buttonBackHome.setOnClickListener((e -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }));
    }

    @Override
    public void onTransactionListReceived(Task<QuerySnapshot> task) {
        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){

            Chat message = new Chat();

            message.setMessage(queryDocumentSnapshot.getString(CONST.MESSAGE_BODY));
            message.setChatUserId(queryDocumentSnapshot.getString(CONST.MESSAGE_CHAT_USER));
            message.setCurrentUserId(queryDocumentSnapshot.getString(CONST.MESSAGE_CURRENT_USER));
            message.setMessageDate(queryDocumentSnapshot.getDate(CONST.MESSAGE_DATE));


            this.messages.add(message);

        }

        if(this.messages.size() > 0){
            Collections.sort(messages);
            chatAdapter = new ChatAdapter(this, R.layout.item_container_sent_message,this.messages);
            binding.messagesList.setAdapter(chatAdapter);
            this.chatService.loading(false,binding);

        }
    }

    @Override
    public void onTransactionListFailed(Task<QuerySnapshot> task) {
        this.chatService.loading(false,binding);
        this.chatService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.no_chat));
    }

    @Override
    public void onTransactionListFailedGettingUser(Task<DocumentSnapshot> task) {

    }

    @Override
    public void onTransactionGetUsers(Task<QuerySnapshot> task) {

    }

    @Override
    public void onTransactionGetUser(Task<DocumentSnapshot> task) {

    }
}