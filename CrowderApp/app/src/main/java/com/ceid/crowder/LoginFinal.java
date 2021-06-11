package com.ceid.crowder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFinal extends AppCompatActivity {

    private Button login;
    private TextView register;
    private EditText memail,mpass;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth = FirebaseAuth.getInstance();

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_final);

        if (fAuth.getInstance().getCurrentUser() == null) {
            //Go to login
        }
        else{
            String uid = fAuth.getInstance().getCurrentUser().getUid();
            GoToMain();
        }

        //Get Username and Password Fields
        memail = findViewById(R.id.email);
        mpass = findViewById(R.id.password);

        //Login Button
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                //Collection and Checking of Data Inserted
                String email = memail.getText().toString().trim();
                String password = mpass.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    memail.setError("Παρακαλώ Εισάγετε Το eMail Σας.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpass.setError("Παρακαλώ Εισάγετε Τον Κωδικό.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginFinal.this,"Η Σύνδεση Ήταν Επιτυχής!",Toast.LENGTH_SHORT).show();
                            GoToMain();
                        }
                        else{
                            Toast.makeText(LoginFinal.this,"Η Σύνδεση Απέτυχε.",Toast.LENGTH_SHORT).show();
                            GoToLogin();
                        }
                    }
                });
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
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

    //Takes To Registration
    public void GoToRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }

    }