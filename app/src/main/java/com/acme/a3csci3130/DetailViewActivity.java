package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField, locationField;
    Business receivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business) getIntent().getSerializableExtra("Business");

        businessNumberField = (EditText) findViewById(R.id.BusinessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.PrimaryBusiness);
        addressField = (EditText) findViewById(R.id.Address);
        locationField = (EditText) findViewById(R.id.Location);
        if (receivedBusinessInfo != null) {
            businessNumberField.setText(receivedBusinessInfo.BusinessNumber);
            nameField.setText(receivedBusinessInfo.name);
            primaryBusinessField.setText(receivedBusinessInfo.PrimaryBusiness);
            addressField.setText(receivedBusinessInfo.Address);
            locationField.setText(receivedBusinessInfo.Location);
        }

        /**
         * Delete button clicked, call method eraseBusiness with the current business key
         */
        Button delete = (Button) findViewById(R.id.deleteButton);
        final String uid= (String) receivedBusinessInfo.getKey();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseBusiness(uid);
            }
        });

        /**
         * Update button clicked, call method updateBusiness with all the new business information
         */
        Button update = (Button) findViewById(R.id.updateButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bNum = businessNumberField.getText().toString();
                String name = nameField.getText().toString();
                String priBus = primaryBusinessField.getText().toString();
                String add = addressField.getText().toString();
                String loc = locationField.getText().toString();
                updateBusiness(uid, bNum, name, priBus, add, loc);
            }
        });
    }

    /**
     *
     * Takes in everything needed to create a business
     * sets the new values in business which changes in the database
     * returns to main activity view
     *
     * @param id
     * @param bNum
     * @param name
     * @param priBus
     * @param add
     * @param loc
     */
    public void updateBusiness(String id, String bNum, String name, String priBus, String add, String loc ){
        DatabaseReference businessReference = FirebaseDatabase.getInstance().getReference("Businesses").child(id);
        Business business = new Business(id, bNum, name, priBus, add, loc);
        businessReference.setValue(business);
        finish();
    }
    /**
     * Deletes the business with the parameter key then returns to main activity view
     *
     * @param key
     */
    public void eraseBusiness(String key) {
        DatabaseReference businessReference = FirebaseDatabase.getInstance().getReference("Businesses").child(key);
        businessReference.removeValue();
        finish();
    }
}
