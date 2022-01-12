package com.example.project.repository;


import android.content.SharedPreferences;

import com.example.project.entity.Chat;

import com.example.project.utils.CONST;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ChatRepo {
    private final FirebaseFirestore db = DB_CONNECTION.getInstance();
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    public void createMessage(Chat chat){
        HashMap<String , Object> data = new HashMap<>();
        data.put(CONST.MESSAGE_CURRENT_USER,chat.getCurrentUserId());
        data.put(CONST.MESSAGE_CHAT_USER,chat.getChatUserId());
        data.put(CONST.MESSAGE_BODY,chat.getMessage());
        data.put(CONST.MESSAGE_DATE,chat.getMessageDate());
        db.collection(CONST.KEY_COLLECTION_MESSAGES).add(data);

//                .addOnSuccessListener(documentReference -> {
//                    Toast.makeText(applicationContext, applicationContext.getString(R.string.user_created_ok), Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Toast.makeText(applicationContext, exception.getMessage(), Toast.LENGTH_SHORT).show();
//                });
    }
}
