package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.entity.User;
import com.example.project.repository.UserRepo;
import com.example.project.service.SignInServiceInterface;
import com.example.project.service.impl.SignInService;
import com.example.project.utils.CONST;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor mPrefsEditor;

    private SignInServiceInterface signInService = new SignInService();
    private UserRepo userRepo = new UserRepo();

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
        this.binding.buttonSignIn.setOnClickListener(e ->{
                        if(this.signInService.isValidSignIn(getApplicationContext(),binding)){
                            this.signInService.singIn(getApplicationContext(),binding);
                        }
                    }
                );
    }


}