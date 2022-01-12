package com.example.project.repository;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityChatBinding;
import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.entity.Chat;

import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.utils.CONST;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ChatRepo {
    private final FirebaseFirestore db = DB_CONNECTION.getInstance();
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    public void loading(Boolean isLoading, ActivityChatBinding binding) {
        if (isLoading){
            binding.progressBarChat.setVisibility(View.VISIBLE);
            binding.buttonSendMessage.setVisibility(View.INVISIBLE);
        }
        else{
            binding.buttonSendMessage.setVisibility(View.VISIBLE);
            binding.progressBarChat.setVisibility(View.INVISIBLE);
        }
    }

    public void createMessage(Chat chat, Context applicationContext,ActivityChatBinding binding){
        loading(true,binding);
        if(chat.getMessage() == null || chat.getMessage().isEmpty()){
            loading(false,binding);
            Toast.makeText(applicationContext, applicationContext.getString(R.string.empty_message), Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String , Object> data = new HashMap<>();
        data.put(CONST.MESSAGE_CURRENT_USER,chat.getCurrentUserId());
        data.put(CONST.MESSAGE_CHAT_USER,chat.getChatUserId());
        data.put(CONST.MESSAGE_BODY,chat.getMessage());
        data.put(CONST.MESSAGE_DATE,chat.getMessageDate());
        db.collection(CONST.KEY_COLLECTION_MESSAGES).add(data)
                .addOnSuccessListener(documentReference -> {
                    loading(false,binding);

                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(applicationContext, applicationContext.getString(R.string.cant_send_message), Toast.LENGTH_SHORT).show();
                });

    }

    public void getMessages(OnTransactionListReceivedListener listener,String currentId,String chatId){
        db.collection(CONST.KEY_COLLECTION_MESSAGES)
                .whereEqualTo(CONST.MESSAGE_CURRENT_USER,currentId)
                .whereEqualTo(CONST.MESSAGE_CHAT_USER,chatId)
                .get()
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
                        listener.onTransactionListReceived(task);
                    }else{
                        listener.onTransactionListFailed(task);
                    }


                });

    }
}
