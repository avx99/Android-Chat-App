package com.example.project.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;
import com.example.project.entity.Chat;
import com.example.project.entity.User;
import com.example.project.utils.CONST;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Chat> {
    private Context mContext;
    private int mResource;

    public ChatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Chat> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView message = convertView.findViewById(R.id.textViewSentMessage);
        TextView date = convertView.findViewById(R.id.textViewDate);

        if(isCurrentUser(getItem(position)))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                message.setBackgroundColor(mContext.getColor(R.color.purple_200));
            }


        }


        message.setText(getItem(position).getMessage());
        date.setText(getItem(position).getMessageDate().toString());

        return convertView;
    }

    private boolean isCurrentUser(Chat message) {
        SharedPreferences sharedpreferences = mContext.getSharedPreferences(CONST.USER_PREF, Context.MODE_PRIVATE);
        String currentId = sharedpreferences.getString(CONST.KEY_USER_ID,"");
        return currentId.equals(message.getCurrentUserId());
    }
}
