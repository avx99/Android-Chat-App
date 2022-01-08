package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.databinding.ActivitySignUpBinding;
import com.example.project.service.SignUpServiceInterface;
import com.example.project.service.impl.SignUpService;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private SignUpServiceInterface signUpService = new SignUpService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        this.binding.textViewToSignIn.setOnClickListener(e -> startActivity(new Intent(getApplicationContext(),SignInActivity.class)));
        this.binding.buttonSignUp.setOnClickListener(e -> {
            if(this.signUpService.isValidSignUp(getApplicationContext(),this.binding,"test"))
                this.signUpService.singUp();
        });

    }
}