package com.ceid.crowder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Home extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth fAuth;
    private SearchView msearch;
    String Search;
    private Button SearchBtn;
    Connection connect;
    String ConnectionResult = "";
    public ResultSet res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth = FirebaseAuth.getInstance();

        //Hide Title Bar Coz Crashes
        getSupportActionBar().hide();

        SearchBtn = findViewById(R.id.searchbtn);

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msearch = findViewById(R.id.search);
                Search = msearch.getQuery().toString();
                StoreSearch(Search);
                GoToResults();
            }
        });

        setContentView(R.layout.activity_home);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout(){
        fAuth.signOut();
        GoToLogin();
    }

    public void GoToResults(){
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }

    public void GoToLogin(){
        Intent intent = new Intent(this, LoginFinal.class);
        startActivity(intent);
    }

    public void StoreSearch(String store){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect != null){
                String query = "SELECT StoreID FROM store WHERE name LIKE" + "%" + Search + "%" + ";";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                res = rs;
            }
            else{
                ConnectionResult = "Ελέγξτε Την Σύνδεση Σας Στο Διαδίκτυο!";
            }
        }
        catch(Exception ex){

        }
    }

}