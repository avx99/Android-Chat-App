package com.example.project.service.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.databinding.ActivityUsersListBinding;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.repository.UserRepo;
import com.example.project.service.HomeServiceInterface;

import java.util.List;

public class HomeService implements HomeServiceInterface {
    OnTransactionListReceivedListener ts;
    private UserRepo userRepo = new UserRepo();
    public HomeService(OnTransactionListReceivedListener ts){
        this.ts = ts;
    }
    @Override
    public void logout(Context context, ActivityHomeBinding activityHomeBinding, SharedPreferences sharedpreferences) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void loading(Boolean isLoading, ActivityHomeBinding binding) {
        if (isLoading){
            binding.progressBarUsersContact.setVisibility(View.VISIBLE);
        }
        else{
            binding.progressBarUsersContact.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void getUser(String currentId) {
        userRepo.getUser(ts,currentId);
    }

    @Override
    public void showToast(Context applicationContext, String message) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUsersByIDs(List<String> ids){
        userRepo.getUsersByIDs(ts,ids);
    }

}
