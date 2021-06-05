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
        setContentView(R.layout.activity_login_final);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                GoToMain();
            }
        });

        register = (TextView) findViewById(R.id.notRegistered);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                GoToRegistration();
            }
        });
    }

     public void GoToMain(){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    public void GoToRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    }