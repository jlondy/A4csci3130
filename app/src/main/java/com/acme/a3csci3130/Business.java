package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String uid;
    public  String BusinessNumber;
    public  String name;
    public  String PrimaryBusiness;
    public  String Address;
    public  String Location;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String uid, String BusinessNumber, String name, String PrimaryBusiness, String Address, String Location){
        this.uid = uid;
        this.BusinessNumber= BusinessNumber;
        this.name = name;
        this.PrimaryBusiness = PrimaryBusiness;
        this.Address =  Address;
        this.Location = Location;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("Business Number", BusinessNumber);
        result.put("name", name);
        result.put("Primary Business", PrimaryBusiness);
        result.put("Address", Address);
        result.put("Location", Location);

        return result;
    }

    public Object getKey() {
        return uid;
    }
}
