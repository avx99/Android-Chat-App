package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.project.R;
import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.entity.User;
import com.example.project.entity.UserAdapter;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.service.HomeServiceInterface;
import com.example.project.service.impl.HomeService;
import com.example.project.utils.CONST;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnTransactionListReceivedListener {
    private ActivityHomeBinding binding;
    private HomeServiceInterface homeService = new HomeService(this);
    private ArrayList<User> users = new ArrayList<>();
    private List<String> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        String currentId = sharedpreferences.getString(CONST.KEY_USER_ID,"");

        homeService.loading(true,binding);
        homeService.getUser(currentId);


        setListeners();
    }
    private void setListeners(){
        this.binding.buttonLogOut.setOnClickListener(e ->{
            SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
            this.homeService.logout(getApplicationContext(),this.binding,sharedpreferences);
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        this.binding.buttonAddContact.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), UsersListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    @Override
    public void onTransactionListReceived(Task<QuerySnapshot> task) {
    }

    @Override
    public void onTransactionListFailed(Task<QuerySnapshot> task) {
        homeService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.fetch_failed));
    }

    @Override
    public void onTransactionListFailedGettingUser(Task<DocumentSnapshot> task) {
        homeService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.fetch_failed));
    }

    @Override
    public void onTransactionGetUsers(Task<QuerySnapshot> task) {
        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){

            User user = new User();
            user.setId(queryDocumentSnapshot.getId());
            user.setEmail(queryDocumentSnapshot.getString(CONST.KEY_EMAIL));
            user.setImage(queryDocumentSnapshot.getString(CONST.KEY_IMAGE));
            user.setName(queryDocumentSnapshot.getString(CONST.KEY_NAME));
            users.add(user);
        }
        if(users.size() > 0){

            UserAdapter userAdapter = new UserAdapter(this,R.layout.item_container_items,users);
            binding.ListView.setAdapter(userAdapter);
            homeService.loading(true,binding);
        }
        else{
            homeService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.no_users));
        }
        homeService.loading(false,binding);
        this.users = users;
    }

    @Override
    public void onTransactionGetUser(Task<DocumentSnapshot> task) {
        DocumentSnapshot documentSnapshot = task.getResult();
        User user = new User();
        user.setId(documentSnapshot.getId());
        user.setEmail(documentSnapshot.getString(CONST.KEY_EMAIL));
        user.setImage(documentSnapshot.getString(CONST.KEY_IMAGE));
        user.setName(documentSnapshot.getString(CONST.KEY_NAME));

        if((List<String>) documentSnapshot.get(CONST.KEY_CONTACTS) != null &&
                ((List<String>) documentSnapshot.get(CONST.KEY_CONTACTS)).size() > 0){
            this.lst = (List<String>) documentSnapshot.get(CONST.KEY_CONTACTS);
            homeService.getUsersByIDs(this.lst);
        }
        else {
            homeService.loading(false,binding);
            homeService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.no_contacts));
        }

    }
}