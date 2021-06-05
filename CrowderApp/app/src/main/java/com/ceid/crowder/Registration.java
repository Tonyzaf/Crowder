package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        //Just The Next Button
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                FinishRegistration();
            }
        });
    }

    public void FinishRegistration() {
        Intent intent = new Intent(this, RegistrationComplete.class);
        startActivity(intent);
    }
}