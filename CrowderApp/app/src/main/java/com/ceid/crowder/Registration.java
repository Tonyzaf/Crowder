package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    Registration(EditText email,EditText username,EditText password){
        this.memail = email;
        this.mpassword = password;
        this.musername = username;
    }

    private Button next;
    EditText musername;
    EditText memail;
    EditText mpassword;
    EditText mreppassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        musername = findViewById(R.id.username);
        memail = findViewById(R.id.regemail);
        mpassword = findViewById(R.id.regpassword);
        mreppassword = findViewById(R.id.password_rep);
        Registration user = new Registration(memail,musername,mpassword);

        //Just The Next Button
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //Collection and Checking of Data Inserted
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String username = musername.getText().toString().trim();
                String reppassword = mreppassword.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    musername.setError("Παρακαλώ Εισάγετε Ένα Όνομα Χρήστη.");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    memail.setError("Παρακαλώ Εισάγετε Μία Διεύθυνση eMail.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Παρακαλώ Εισάγετε Έναν Κωδικό.");
                    return;
                }

                if(TextUtils.isEmpty(reppassword)){
                    mreppassword.setError("Παρακαλώ Εισάγετε Ξανά Τον Κωδικό.");
                    return;
                }

                if(password.length() < 6){
                    mpassword.setError("Ο Κωδικός Πρέπει να Περιέχει Τουλάχιστον 6 Ψηφία");
                    return;
                }

                FinishRegistration();
            }
        });
    }

    public String getemail(){
        return (memail.getText().toString().trim());
    }

    public String getusername(){
        return (musername.getText().toString().trim());
    }

    public String getpass(){
        return (mpassword.getText().toString().trim());
    }

    public void FinishRegistration() {
        Intent intent = new Intent(this, RegistrationComplete.class);
        startActivity(intent);
    }
}