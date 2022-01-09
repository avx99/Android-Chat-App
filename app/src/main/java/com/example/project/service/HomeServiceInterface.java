package com.example.project.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.databinding.ActivityHomeBinding;

public interface HomeServiceInterface {
    void logout(Context context, ActivityHomeBinding activityHomeBinding, SharedPreferences sharedpreferences);
}
