package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> T1,T2,T3;
    DBHelper DB;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        T1 = new ArrayList<>();
        T2 = new ArrayList<>();
        T3 = new ArrayList<>();
        recyclerView = findViewById(R.id.rec);
        adapter = new MyAdapter(this, T1, T2, T3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        displayData();
    }
    private void displayData(){
        Cursor c = DB.getdata();
        while(c.moveToNext()){
            T1.add(c.getString(1));
            T2.add(c.getString(2));
            T3.add(c.getString(0));
        }
    }
}