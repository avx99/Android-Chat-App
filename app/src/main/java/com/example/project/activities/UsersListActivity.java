package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.R;
import com.example.project.databinding.ActivityUsersListBinding;
import com.example.project.entity.User;
import com.example.project.entity.UserAdapter;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {
    private ActivityUsersListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersListBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        ArrayList<User> lst = new ArrayList<>();
        lst.add(new User("aa@zz.cc","1235"));
        lst.add(new User("bb@zz.cc","de"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));
        lst.add(new User("cc@zz.cc","cee54"));

        UserAdapter userAdapter = new UserAdapter(this,R.layout.item_container_items,lst);
        binding.ListViewPickUser.setAdapter(userAdapter);

        setListeners();
    }

    private void setListeners(){
        binding.buttonBack.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}