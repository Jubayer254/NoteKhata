package com.example.assignment4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {
    private Context context;
    private ArrayList title, details,id, b_flag, c_flag;
    private Cursor cursor;
    DBHelper DB;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry3, parent,false);
        return new MyViewHolder(v);
    }


    public MyAdapter3(Context context, ArrayList title, ArrayList details, ArrayList id) {
        this.context = context;
        this.title = title;
        this.details = details;
        this.id = id;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(String.valueOf(title.get(position)));
        holder.details.setText(String.valueOf(details.get(position)));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, details;
        String id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.T2);
            details = itemView.findViewById(R.id.T4);
        }
    }
}

