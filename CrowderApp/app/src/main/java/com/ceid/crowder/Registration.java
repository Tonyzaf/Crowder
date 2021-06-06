package com.ceid.crowder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private Button next;
    private CheckBox terms;
    private boolean accepted = false;
    private EditText musername;
    private EditText memail;
    private EditText mpassword;
    private EditText mreppassword;

    ProgressBar registrationprogress;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getInstance().getCurrentUser() == null) {
            //Go to login
        }
        else{
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            GoToMain();
        }

        registrationprogress = findViewById(R.id.registrationprogress2);

        //Terms of Service Checkbox
        terms = (CheckBox) findViewById(R.id.terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                accepted = true;
            }
        });

        musername = findViewById(R.id.username);
        memail = findViewById(R.id.regemail);
        mpassword = findViewById(R.id.regpassword);
        mreppassword = findViewById(R.id.password_rep);

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

                //Check if Terms Accepted
                if(accepted) {

                    //Enable The Progress Bar
                    registrationprogress.setVisibility(View.VISIBLE);

                    //Register To Firebase
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this,"Η Εγγραφή Ήταν Επιτυχής!",Toast.LENGTH_SHORT).show();
                                GoToLogin();
                            }
                            else{
                                Toast.makeText(Registration.this,"Η Εγγραφή Απέτυχε." + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else{
                    terms.setError( "Πρέπει Να Αποδεχτείτε Τους Όρους Για Να Συνεχίσετε!" );
                }
            }
        });
    }

    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }

    public void GoToMain(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}