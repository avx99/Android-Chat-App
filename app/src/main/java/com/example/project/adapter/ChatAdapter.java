package com.example.project.adapter;

import android.content.Context;
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


        message.setText(getItem(position).getMessage());
        date.setText(getItem(position).getMessageDate().toString());

        return convertView;
    }
}
