package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.project.databinding.ActivitySignInBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        this.binding.textViewCreateAccount.setOnClickListener(e ->
                    startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        this.binding.buttonSignIn.setOnClickListener(e ->
                        addDataToFirestore()
                );
    }

    private void addDataToFirestore(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String , Object> data = new HashMap<>();
        data.put("first_name","oussama");
        data.put("last_name","abouzid");
        database.collection("user")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(),"data insered",Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
        });

    }
}