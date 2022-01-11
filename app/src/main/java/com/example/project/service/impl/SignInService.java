package com.example.project.service.impl;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.entity.User;
import com.example.project.repository.UserRepo;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.service.SignInServiceInterface;

public class SignInService implements SignInServiceInterface {

    OnTransactionListReceivedListener ts;
    public SignInService(OnTransactionListReceivedListener ts){
        this.ts=ts;

    }

    private UserRepo userRepo=new UserRepo();
    @Override
    public Boolean isValidSignIn(Context applicationContext, ActivitySignInBinding binding) {
        if(binding.editTextTextEmailAddress.getText().toString().trim().isEmpty()){
            binding.editTextTextEmailAddress.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_email));
            return false;
        }
        else if(binding.editTextTextPassword.getText().toString().trim().isEmpty()){
            binding.editTextTextPassword.setError("");
            binding.editTextTextPassword.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_password));
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextEmailAddress.getText().toString()).matches()) {
            binding.editTextTextEmailAddress.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.enter_valid_email));
            return false;
        }
        else
            return true;
    }

    @Override
    public void singIn(ActivitySignInBinding binding){
        this.loading(true,binding);
        this.userRepo.signIn(this.getUser(binding),ts);
    }

    @Override
    public void showToast(Context applicationContext, String message) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(Boolean isLoading, ActivitySignInBinding binding) {
        if (isLoading){
            binding.progressBarSignIn.setVisibility(View.VISIBLE);
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
        }
        else{
            binding.buttonSignIn.setVisibility(View.VISIBLE);
            binding.progressBarSignIn.setVisibility(View.INVISIBLE);
        }
    }

    public User getUser(ActivitySignInBinding binding) {
        return new User(binding.editTextTextEmailAddress.getText().toString(),
                binding.editTextTextPassword.getText().toString());
    }
}
