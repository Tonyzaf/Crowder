package com.ceid.crowder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        GoToLogin();
    }

    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }
}