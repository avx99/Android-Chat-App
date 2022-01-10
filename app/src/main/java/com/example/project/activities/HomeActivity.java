package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project.R;
import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.entity.User;
import com.example.project.entity.UserAdapter;
import com.example.project.service.HomeServiceInterface;
import com.example.project.service.impl.HomeService;
import com.example.project.utils.CONST;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeServiceInterface homeService = new HomeService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        ArrayList<User> lst = new ArrayList<>();
        lst.add(new User("oussama@usms.com","1235"));
        lst.add(new User("badr@usp.ma","de"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));
//        lst.add(new User("cc@zz.cc","cee54"));

        UserAdapter userAdapter = new UserAdapter(this,R.layout.item_container_items,lst);
        binding.ListView.setAdapter(userAdapter);

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

}