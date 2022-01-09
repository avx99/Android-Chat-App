package com.example.project.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.example.project.databinding.ActivitySignUpBinding;
import com.example.project.entity.User;

public interface SignUpServiceInterface{
    Boolean isValidSignUp(Context applicationContext,ActivitySignUpBinding binding, String image);
    void singUp(Context applicationContext,ActivitySignUpBinding binding);
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading,ActivitySignUpBinding binding);
    String encodeImage(Bitmap bitmap);
    void addUser(Context applicationContext, ActivitySignUpBinding binding);
    User getUser(ActivitySignUpBinding binding);
}
