package com.example.project.service;

import com.example.project.entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public interface OnTransactionListReceivedListener {
    void onTransactionListReceived(Task<QuerySnapshot> task);
    void onTransactionListFailed(Task<QuerySnapshot> task);
    ArrayList<User> onTransactionGetUsers(Task<QuerySnapshot> task);
}
