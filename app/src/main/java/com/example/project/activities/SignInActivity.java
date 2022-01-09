package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.example.project.R;
import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.entity.User;
import com.example.project.exceptions.NotAllowedToLoggIn;
import com.example.project.repository.UserRepo;
import com.example.project.service.OnTransactionListReceivedListener;
import com.example.project.service.SignInServiceInterface;
import com.example.project.service.impl.SignInService;
import com.example.project.utils.CONST;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;


public class SignInActivity extends AppCompatActivity implements OnTransactionListReceivedListener {
    private ActivitySignInBinding binding;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    SignInServiceInterface signInService = new SignInService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignInBinding.inflate(getLayoutInflater());
        this.sharedPreferences = getSharedPreferences(CONST.USER_PREF,Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        sharedPreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(CONST.KEY_IS_SIGNED_IN, false)){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        setContentView(binding.getRoot());
        setListeners();
    }


    private void setListeners(){
        this.binding.textViewCreateAccount.setOnClickListener(e ->
                    startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        this.binding.buttonSignIn.setOnClickListener(e ->{
                    SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
                    Boolean isLogged = sharedpreferences.getBoolean(CONST.KEY_IS_SIGNED_IN, false);
                    Log.d("ISLOGGED", isLogged ? "oui" : "non");
                    Log.d("ISLOGGED nom", sharedpreferences.getString(CONST.KEY_NAME, "walo"));
                    Log.d("ISLOGGED email", sharedpreferences.getString(CONST.KEY_EMAIL, "walo"));
                    Log.d("ISLOGGED image", sharedpreferences.getString(CONST.KEY_IMAGE, "walo"));

                        if(this.signInService.isValidSignIn(getApplicationContext(),binding)){
                            this.signInService.singIn(getApplicationContext(),binding, editor);
                        }
                    }
                );
    }


    @Override
    public void onTransactionListReceived(Task<QuerySnapshot> task) {
        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
        SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        editor.putBoolean(CONST.KEY_IS_SIGNED_IN, true);
        editor.putString(CONST.KEY_USER_ID, documentSnapshot.getId());
        editor.putString(CONST.KEY_NAME, documentSnapshot.getString(CONST.KEY_NAME));
        editor.putString(CONST.KEY_EMAIL, documentSnapshot.getString(CONST.KEY_EMAIL));
        editor.putString(CONST.KEY_IMAGE, documentSnapshot.getString(CONST.KEY_IMAGE));
        editor.commit();

        Toast.makeText(this, this.getString(R.string.logged), Toast.LENGTH_SHORT).show();
        Boolean isLogged = sharedpreferences.getBoolean(CONST.KEY_IS_SIGNED_IN, false);

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }

    @Override
    public void onTransactionListFailed(Task<QuerySnapshot> task) {
        Toast.makeText(this, this.getString(R.string.not_logged), Toast.LENGTH_SHORT).show();
        signInService.loading(false,binding);
    }
}