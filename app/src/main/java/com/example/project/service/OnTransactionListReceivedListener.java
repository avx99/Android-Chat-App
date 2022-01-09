package com.example.project.service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface OnTransactionListReceivedListener {
    void onTransactionListReceived(Task<QuerySnapshot> task);
    void onTransactionListFailed(Task<QuerySnapshot> task);
}
