package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;



    page1 p1 = new page1();
    page2 p2 = new page2();
    page3 p3 = new page3();
    page4 p4 = new page4();
    help h = new help();
    about a = new about();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p3).commit();


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        frameLayout = findViewById(R.id.main_frame);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.spage1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,h).commit();
                        return true;
                    case R.id.spage2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,a).commit();
                        return true;
                }
                return false;
            }
        });

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p1).commit();
                        return true;
                    case R.id.page2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p2).commit();
                        return true;
                    case R.id.page3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p3).commit();
                        return true;
                    case R.id.page4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p4).commit();
                        return true;

                }

                return false;
            }
        });

    }

}