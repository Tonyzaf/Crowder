package com.ceid.crowder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.appcompat.app.AppCompatActivity;

public class FinishRegistration extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ScrollView stores;
    StorageReference storageRef;
    private EditText mname;
    private EditText maddress;
    private EditText mpostcode;
    private EditText mcity;
    private Button finish;
    private String name;
    private String address;
    private String postcode;
    private String city;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_finishregistration);

        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

        if (fAuth.getInstance().getCurrentUser() == null) {
            GoToLogin();
        }

        finish = findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mname.getText().toString().trim();
                address = maddress.getText().toString().trim();
                postcode = mpostcode.getText().toString().trim();
                city = mcity.getText().toString().trim();

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

                addDatatoFirebase(name, address, postcode, city);
            }
        });

        //Check For Login
        if (fAuth.getInstance().getCurrentUser() == null) {
            GoToLogin();
        }

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_finishregistration);

    }

    private void addDatatoFirebase(String name, String address, String postcode, String city) {
        // below 3 lines of code is used to set
        // data in our object class.
        user.SetName(name);
        user.SetCity(city);
        user.SetAddress(address);
        user.SetPostCode(postcode);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(user);

                // after adding this data we are showing toast message.
                Toast.makeText(FinishRegistration.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled( DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(FinishRegistration.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
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