package com.example.project.service.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivitySignUpBinding;
import com.example.project.service.SignUpServiceInterface;

import java.util.regex.Pattern;

public class SignUpService implements SignUpServiceInterface {


    @Override
    public Boolean isValidSignUp(Context applicationContext,ActivitySignUpBinding binding,String image) {
        if(image == null){
//            binding.editTextPasswordNewConfirmation.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_image));
            return false;
        }
        else if(binding.editTextPersonNameNew.getText().toString().trim().isEmpty()){
            binding.editTextPersonNameNew.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_name));
            return false;
        }
        else if(binding.editTextEmailAddressNew.getText().toString().trim().isEmpty()){
            binding.editTextEmailAddressNew.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_email));
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmailAddressNew.getText().toString()).matches()){
            binding.editTextEmailAddressNew.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.enter_valid_email));
            return false;
        }
        else if(binding.editTextPasswordNew.getText().toString().trim().isEmpty()){
            binding.editTextPasswordNew.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.select_password));
            return false;
        }
        else if(binding.editTextPasswordNewConfirmation.getText().toString().trim().isEmpty()){
            binding.editTextPasswordNewConfirmation.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.confirm_password));
            return false;
        }
        else if(!binding.editTextPasswordNew.getText().toString().equals(binding.editTextPasswordNewConfirmation.getText().toString())){
            binding.editTextPasswordNewConfirmation.setError("");
            binding.editTextPasswordNew.setError("");
            this.showToast(applicationContext, applicationContext.getString(R.string.match_password));
            return false;
        }
        return true;
    }

    @Override
    public void singUp() {

    }

    @Override
    public void showToast(Context applicationContext, String message) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(Boolean isLoading) {

    }

}
