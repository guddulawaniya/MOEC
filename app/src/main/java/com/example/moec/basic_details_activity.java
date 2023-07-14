package com.example.moec;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class basic_details_activity extends AppCompatActivity {


    private RequestQueue mRequestQueue;
     private  TextView country,state,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);



        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Basic Information");
        cleartext.setVisibility(View.GONE);

        TextView studentname = findViewById(R.id.studentname);
        TextView emailaddress = findViewById(R.id.emailaddress);
        TextView dob = findViewById(R.id.dob);
        TextView gender = findViewById(R.id.gender);
         country = findViewById(R.id.country);
         state = findViewById(R.id.state);
         city = findViewById(R.id.city);
        TextView number = findViewById(R.id.mobilenumber);

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);




        String firstname = preferences.getString("Fname",null);
        String lastname =   preferences.getString("Lname",null);
        String emailid =  preferences.getString("email",null);
        String contact =  preferences.getString("number",null);
        String dobget=  preferences.getString("DOb",null);
        String countryget=  preferences.getString("Country",null);
        String stateget=  preferences.getString("State",null);
        String Cityget=  preferences.getString("City",null);
        String genderget=  preferences.getString("g",null);




        studentname.setText(firstname+" "+lastname);
        emailaddress.setText(emailid);
        dob.setText(dobget);
        gender.setText(genderget);
        country.setText(countryget);
        state.setText(stateget);
        city.setText(Cityget);
        number.setText(contact);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


}