package com.example.project.service;

import android.content.Context;

import com.example.project.databinding.ActivitySignInBinding;

public interface SignInServiceInterface {
    Boolean isValidSignIn(Context applicationContext, ActivitySignInBinding binding);
    void singIn(Context applicationContext, ActivitySignInBinding binding);
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading,ActivitySignInBinding binding);

}
