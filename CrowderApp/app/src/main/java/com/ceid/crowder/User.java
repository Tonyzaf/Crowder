package com.ceid.crowder;

public class User {

    // string variable for
    // storing UserID.
    private String UserID;

    // string variable for
    // storing name.
    private String Name;

    // string variable for storing
    // User City
    private String City;

    // string variable for storing
    // address.
    private String Address;

    // string variable for storing
    // postcode.
    private String PostCode;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public User() {

    }

    // created getter and setter methods
    // for all our variables.

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String Name) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void SetName(String Name) {
        this.Name = Name;
    }

    public String GetCity() {
        return City;
    }

    public void SetCity(String City) {
        this.City = City;
    }

    public String GetAddress() {
        return Address;
    }

    public void SetAddress(String Address) {
        this.Address = Address;
    }

    public String GetPostCode() {
        return PostCode;
    }

    public void SetPostCode(String PostCode) {
        this.PostCode = PostCode;
    }
}