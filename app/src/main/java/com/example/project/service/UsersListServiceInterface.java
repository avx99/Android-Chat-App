package com.example.project.service;


import android.content.Context;

import com.example.project.databinding.ActivityUsersListBinding;

public interface UsersListServiceInterface {
    void loading(Boolean isLoading, ActivityUsersListBinding binding);
    void showToast(Context applicationContext, String message);
    void getUsers();
    void addUserToContacts(String currentId,String id);
}
