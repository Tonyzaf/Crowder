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

public class RegistrationComplete extends AppCompatActivity {

    private CheckBox terms;
    private Button register;
    private boolean accepted = false;
    private String email,username,password;

    EditText mname,maddress,mpostcode,mcity;
    ProgressBar registrationprogress;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email = Registration.getemail();
        username = Registration.getusername();
        password = Registration.getpass();

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();

        mname = findViewById(R.id.name);
        maddress = findViewById(R.id.address);
        mpostcode = findViewById(R.id.postCode);
        mcity = findViewById(R.id.city);

        fAuth = FirebaseAuth.getInstance();

        registrationprogress = findViewById(R.id.registrationprogress);

        if(fAuth.getCurrentUser() != null){
            GoToMain();
        }

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

                String name = mname.getText().toString().trim();
                String address = maddress.getText().toString().trim();
                String postcode = mpostcode.getText().toString().trim();
                String city = mcity.getText().toString().trim();

                if(TextUtils.isEmpty(address)){
                    maddress.setError("Παρακαλώ Εισάγετε Μία Διεύθυνση.");
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    mname.setError("Παρακαλώ Εισάγετε Το Ονοματεπώνυμο Σας.");
                    return;
                }

                if(TextUtils.isEmpty(postcode)){
                    mpostcode.setError("Παρακαλώ Εισάγετε Τον Ταχυδρομικό Σας Κώδικα.");
                    return;
                }

                if(TextUtils.isEmpty(city)){
                    mcity.setError("Παρακαλώ Εισάγετε Ένα Όνομα Χρήστη.");
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
                                Toast.makeText(RegistrationComplete.this,"Η Εγγραφή Ήταν Επιτυχής!",Toast.LENGTH_SHORT).show();
                                GoToLogin();
                            }
                            else{
                                Toast.makeText(RegistrationComplete.this,"Η Εγγραφή Απέτυχε." + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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

    //Goes to Login
    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }

    public void GoToMain(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}