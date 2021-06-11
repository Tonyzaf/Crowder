package com.ceid.crowder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FinishRegistration<Firebase> extends AppCompatActivity {

    FirebaseAuth fAuth;
    DatabaseReference dbref;
    ScrollView stores;
    StorageReference storageRef;
    private EditText mname;
    private EditText maddress;
    private EditText mpostcode;
    private EditText mcity;
    private Button finishbtn;
    private String name;
    private String address;
    private String postcode;
    private String city;
    private String userid;
    private ResultSet rs;
    Connection connect;
    String ConnectionResult = "";
    ListView UserInfo;
    FirebaseUser fuser;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_finishregistration);

        fAuth = FirebaseAuth.getInstance();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Users");


        user = new User();

        if (fAuth.getInstance().getCurrentUser() == null) {
            GoToLogin();
        }

        finishbtn = (Button) findViewById(R.id.finish);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets UserID
                userid = fAuth.getUid();
                user.setUserID(userid);
                //Gets Input Data
                name = mname.getText().toString().trim();
                user.SetName(name);
                address = maddress.getText().toString().trim();
                user.SetAddress(address);
                postcode = mpostcode.getText().toString().trim();
                user.SetPostCode(postcode);
                city = mcity.getText().toString().trim();
                user.SetCity(city);

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

                InsertUserData(userid,name,address,city,postcode);
                GoToMain();
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

    public void InsertUserData(String userid, String name, String Address, String City, String Postcode){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect != null){
                String query = "INSERT INTO users VALUES (" + userid  + "," + name + "," + Address + "," + City + "," + Postcode + ");" ;
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
            else{
                ConnectionResult = "Ελέγξτε Την Σύνδεση Σας Στο Διαδίκτυο!";
            }
        }
        catch(Exception ex){

        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        GoToLogin();
    }

    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }

    public void GoToMain(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}