package com.ceid.crowder;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class ConnectionHelper {
    Connection con;
    String uname,pass,ip,port,database;

    public ConnectionHelper(){

    }

    @SuppressLint("NewApi")
    public Connection connectionclass(){
        ip = "35.234.101.122";
        database = "crowder";
        uname = "root";
        pass = "kwdikos123";
        port = "3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "database=" + database + ";user" + uname + ";password" + pass + ";";
                connection = DriverManager.getConnection(ConnectionURL);
            } catch (SQLException | ClassNotFoundException ex) {
                Log.e("Error ", ex.getMessage());
            }

        return connection;
    }
}
