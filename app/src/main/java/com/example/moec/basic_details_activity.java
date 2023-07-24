package com.example.moec;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class basic_details_activity extends AppCompatActivity {

    private TextView studentname,emailaddress,dob,gender,country,state,city,number,pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);




        studentname = findViewById(R.id.studentname);
        emailaddress = findViewById(R.id.emailaddress);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        number = findViewById(R.id.mobilenumber);
        pincode = findViewById(R.id.pincode);



        // Toolbar Expressions

        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);




        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    @Override
    protected void onStart() {
        // get the data and set data on screen On start Activity
        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        studentname.setText(preferences.getString("Fname", null) + preferences.getString("Lname", null));
        emailaddress.setText(preferences.getString("email", null));
        number.setText(preferences.getString("number", null));
        dob.setText(preferences.getString("DOb", null));
        gender.setText(preferences.getString("g", null));
        country.setText(preferences.getString("Country", null));
        state.setText(preferences.getString("State", null));
        city.setText(preferences.getString("City", null));
        pincode.setText(preferences.getString("pincode", null));


        super.onStart();
    }
}