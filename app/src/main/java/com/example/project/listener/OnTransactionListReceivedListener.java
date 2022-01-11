package com.example.project.listener;

import com.example.project.entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public interface OnTransactionListReceivedListener {
    void onTransactionListReceived(Task<QuerySnapshot> task);
    void onTransactionListFailed(Task<QuerySnapshot> task);
    void onTransactionListFailedGettingUser(Task<DocumentSnapshot> task);
    void onTransactionGetUsers(Task<QuerySnapshot> task);
    void onTransactionGetUser(Task<DocumentSnapshot> task);
}
