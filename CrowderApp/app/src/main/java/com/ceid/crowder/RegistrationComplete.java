package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class RegistrationComplete extends AppCompatActivity {

    private CheckBox terms;
    private Button register;
    private boolean accepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration_complete);

        //Terms of Service Checkbox
        terms = (CheckBox) findViewById(R.id.Terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                accepted = true;
            }
        });

        //Registration Button
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //Check if Terms Accepted
                if(accepted) {
                    GoToMain();
                }
                else{
                    terms.setError( "Πρέπει Να Αποδεχτείτε Τους Όρους Για Να Συνεχίσετε!" );
                }
            }
        });
    }

    //Goes to Main
    public void GoToMain(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}