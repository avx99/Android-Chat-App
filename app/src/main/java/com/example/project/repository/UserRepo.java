package com.example.project.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivitySignInBinding;
import com.example.project.entity.User;
import com.example.project.utils.CONST;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class UserRepo {
    private final FirebaseFirestore db = DB_CONNECTION.getInstance();
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    public void createUser(Context applicationContext, User user){
        HashMap<String , Object> data = new HashMap<>();
        data.put(CONST.KEY_NAME,user.getName());
        data.put(CONST.KEY_EMAIL,user.getEmail());
        data.put(CONST.KEY_PASSWORD,user.getPassword());
        data.put(CONST.KEY_IMAGE,user.getImage());
        db.collection(CONST.KEY_COLLECTION_USERS)
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(applicationContext, applicationContext.getString(R.string.user_created_ok), Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(applicationContext, exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void loading(Boolean isLoading, ActivitySignInBinding binding) {
        if (isLoading){
            binding.progressBarSignIn.setVisibility(View.VISIBLE);
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
        }
        else{
            binding.buttonSignIn.setVisibility(View.VISIBLE);
            binding.progressBarSignIn.setVisibility(View.INVISIBLE);
        }
    }

    public void signIn(Context applicationContext, User user,ActivitySignInBinding binding){

        db.collection(CONST.KEY_COLLECTION_USERS)
                .whereEqualTo(CONST.KEY_EMAIL,user.getEmail())
                .whereEqualTo(CONST.KEY_PASSWORD,user.getPassword())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                        mPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
                        mPrefsEditor = mPrefs.edit();
                        mPrefsEditor.putBoolean(CONST.KEY_IS_SIGNED_IN,true);
                        mPrefsEditor.putString(CONST.KEY_USER_ID,documentSnapshot.getId());
                        mPrefsEditor.putString(CONST.KEY_NAME,documentSnapshot.getString(CONST.KEY_NAME));
                        mPrefsEditor.putString(CONST.KEY_EMAIL,documentSnapshot.getString(CONST.KEY_EMAIL));
                        mPrefsEditor.putString(CONST.KEY_IMAGE,documentSnapshot.getString(CONST.KEY_IMAGE));
                        mPrefsEditor.commit();


                        Toast.makeText(applicationContext, applicationContext.getString(R.string.logged), Toast.LENGTH_SHORT).show();
                    }else
                        loading(false,binding);
                        Toast.makeText(applicationContext, applicationContext.getString(R.string.not_logged), Toast.LENGTH_SHORT).show();
                });

    }

    public User getUserByEmail(User user){
        List<DocumentSnapshot> lst = db.collection(CONST.KEY_COLLECTION_USERS)
                .whereEqualTo(CONST.KEY_EMAIL,user.getEmail())
                .get()
                .getResult()
                .getDocuments();
        for(int i = 0;i < lst.size();i++){
            Log.d("LSTSNP",lst.get(i).toString());
        }
        return null;
    }
}
