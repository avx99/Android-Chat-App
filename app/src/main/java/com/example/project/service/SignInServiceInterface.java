package com.example.project.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.exceptions.NotAllowedToLoggIn;

public interface SignInServiceInterface {
    Boolean isValidSignIn(Context applicationContext, ActivitySignInBinding binding);
    void singIn(ActivitySignInBinding binding);
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading,ActivitySignInBinding binding);

}
