package com.example.project.service.impl;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.project.databinding.ActivityUsersListBinding;
import com.example.project.repository.UserRepo;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.service.UsersListServiceInterface;

public class UsersListService implements UsersListServiceInterface {
    private UserRepo userRepo = new UserRepo();
    OnTransactionListReceivedListener ts;

    public UsersListService(OnTransactionListReceivedListener ts){
        this.ts=ts;

    }



    @Override
    public void loading(Boolean isLoading, ActivityUsersListBinding binding) {
        if (isLoading){
            binding.progressBarUsersList.setVisibility(View.VISIBLE);
        }
        else{
            binding.progressBarUsersList.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showToast(Context applicationContext, String message) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUsers() {
        userRepo.getUsers(ts);
    }

    @Override
    public void addUserToContacts(String currentId,String id) {
        userRepo.addUserToContacts(currentId,id);
    }
}
