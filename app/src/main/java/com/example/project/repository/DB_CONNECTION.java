package com.example.project.repository;

import com.google.firebase.firestore.FirebaseFirestore;

public class DB_CONNECTION {

    static FirebaseFirestore database = null;

    private DB_CONNECTION(){
    }

    public static FirebaseFirestore getInstance(){
        if(database == null)
            database = FirebaseFirestore.getInstance();
        return database;
    }
}
