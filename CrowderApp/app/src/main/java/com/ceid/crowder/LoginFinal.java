package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginFinal extends AppCompatActivity {

    private Button login;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_final);

        //Login Button
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                GoToMain();
            }
        });

        //No Account Text
        register = (TextView) findViewById(R.id.notRegistered);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                GoToRegistration();
            }
        });
    }

    //Takes You Home
     public void GoToMain(){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    //Takes To Registration
    public void GoToRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    }