package com.example.project.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.service.HomeServiceInterface;

public class HomeService implements HomeServiceInterface {
    @Override
    public void logout(Context context, ActivityHomeBinding activityHomeBinding, SharedPreferences sharedpreferences) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }


}
