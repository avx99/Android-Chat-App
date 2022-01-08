package com.example.project.service;

import android.content.Context;
import android.content.Intent;

import com.example.project.databinding.ActivitySignUpBinding;

public interface SignUpServiceInterface{
    Boolean isValidSignUp(Context applicationContext,ActivitySignUpBinding binding, String image);
    void singUp();
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading);
}
