package com.example.project.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.databinding.ActivityHomeBinding;

import java.util.List;

public interface HomeServiceInterface {
    void logout(Context context, ActivityHomeBinding activityHomeBinding, SharedPreferences sharedpreferences);
    void showToast(Context applicationContext, String message);
    void loading(Boolean isLoading, ActivityHomeBinding binding);
    void getUser(String currentId);
    void getUsersByIDs(List<String> ids);
}
