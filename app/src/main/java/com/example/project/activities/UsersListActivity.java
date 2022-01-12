package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityUsersListBinding;
import com.example.project.entity.User;
import com.example.project.adapter.UserAdapter;
import com.example.project.listener.OnTransactionListReceivedListener;
import com.example.project.service.UsersListServiceInterface;
import com.example.project.service.impl.UsersListService;
import com.example.project.utils.CONST;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity implements OnTransactionListReceivedListener {
    private ActivityUsersListBinding binding;
    private UsersListServiceInterface usersListService = new UsersListService(this);
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersListBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        usersListService.loading(true,binding);
        usersListService.getUsers();

        setListeners();
    }

    private void setListeners(){
        binding.buttonBack.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        binding.ListViewPickUser.setOnItemClickListener((adapterView, view, i, l) -> {
            SharedPreferences sharedPreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
            String currentId = sharedPreferences.getString(CONST.KEY_USER_ID,"");
            String chatUserId = users.get(i).getId();
            usersListService.addUserToContacts(currentId,chatUserId);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),users.get(i).getName() + " " +
                    getApplicationContext().getString(R.string.add_contact),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onTransactionListReceived(Task<QuerySnapshot> task) {

    }

    @Override
    public void onTransactionListFailed(Task<QuerySnapshot> task) {
        usersListService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.fetch_failed));
    }

    @Override
    public void onTransactionListFailedGettingUser(Task<DocumentSnapshot> task) {

    }

    @Override
    public void onTransactionGetUsers(Task<QuerySnapshot> task) {

        ArrayList<User> users = new ArrayList<>();
        SharedPreferences sharedpreferences = getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        String currentUserID =  sharedpreferences.getString(CONST.KEY_USER_ID, "");
        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
            if(currentUserID.equals(queryDocumentSnapshot.getId())){
                continue;
            }
            User user = new User();
            user.setId(queryDocumentSnapshot.getId());
            user.setEmail(queryDocumentSnapshot.getString(CONST.KEY_EMAIL));
            user.setImage(queryDocumentSnapshot.getString(CONST.KEY_IMAGE));
            user.setName(queryDocumentSnapshot.getString(CONST.KEY_NAME));
            users.add(user);
        }
        if(users.size() > 0){

            UserAdapter userAdapter = new UserAdapter(this,R.layout.item_container_items,users);
            binding.ListViewPickUser.setAdapter(userAdapter);usersListService.loading(true,binding);
        }
        else{
            usersListService.showToast(getApplicationContext(),getApplicationContext().getString(R.string.no_users));
        }
        usersListService.loading(false,binding);
        this.users = users;
    }

    @Override
    public void onTransactionGetUser(Task<DocumentSnapshot> task) {

    }
}