package com.example.moec;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class basic_details_activity extends AppCompatActivity {

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
        TextView maritalstatus = findViewById(R.id.maritalstatus);
        TextView country = findViewById(R.id.country);
        TextView state = findViewById(R.id.state);
        TextView city = findViewById(R.id.city);
        TextView number = findViewById(R.id.mobilenumber);

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String firstname = preferences.getString("Fname",null);
        String lastname =   preferences.getString("Lname",null);
        String emailid =  preferences.getString("Email",null);
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