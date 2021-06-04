package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginFinal extends AppCompatActivity {

    private Button login;
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
    }

     public void GoToMain(){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }