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
import com.example.project.entity.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private int mResource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView sub = convertView.findViewById(R.id.textViewContainerSub);
        TextView name = convertView.findViewById(R.id.textViewContainerName);


        name.setText(getItem(position).getName());
        sub.setText(getItem(position).getEmail());

        return convertView;
    }

}
