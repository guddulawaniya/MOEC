package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.loginActivity.login_Activity;


public class profile_dashboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dashboard);


        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView basic_information = findViewById(R.id.basic_information);
        TextView bank_details = findViewById(R.id.bank_details);
        TextView my_Documents = findViewById(R.id.my_Documents);
        TextView my_transations = findViewById(R.id.my_transations);

        TextView logout = findViewById(R.id.logout);
        TextView studentname = findViewById(R.id.studentname);
        TextView contactbnumber = findViewById(R.id.contactbnumber);
        TextView emailaddress = findViewById(R.id.emailaddress);
        TextView dob = findViewById(R.id.dob);
        TextView gender = findViewById(R.id.gender);
        ImageView setimage = findViewById(R.id.setimage);

        TextView my_preference_profile = findViewById(R.id.my_preference_profile);

        TextView cleartext = findViewById(R.id.cleartext);
        toolbartitle.setText("My Profile");
        cleartext.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_settings_24,0);
        cleartext.setText("");

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String firstname = preferences.getString("Fname","");
        String lastname =   preferences.getString("Lname","");
        String emailid =  preferences.getString("email","");
        String contact =  preferences.getString("number","");
        String dobget=  preferences.getString("DOb","");
        String genderget=  preferences.getString("g","");

        String imageurl = preferences.getString("image",null);
        if (imageurl != null) {
            byte[] decodedBytes = Base64.decode(imageurl, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            // Display the decodedBitmap in an ImageView
            setimage.setImageBitmap(decodedBitmap);
        }





        if (dobget.equals("null"))
        {
            dob.setText("DD/MM/YYYY");
        }
        else dob.setText(dobget);
        if (genderget.equals("null"))
        {
            gender.setText("Student");

        }
        else gender.setText(genderget+" | Student");

        //set data on load activity

        studentname.setText(firstname+" "+lastname);

        emailaddress.setText(emailid);
        contactbnumber.setText("+91-"+contact);



        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Setting_Actvity.class));
            }
        });

        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                editor.putString("userid",null);
                editor.commit();
                startActivity(new Intent(getApplicationContext(), login_Activity.class));
                finish();
            }
        });
        bank_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), bank_details.class));
            }
        });
        basic_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), basic_details_activity.class));
            }
        });
        my_Documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), My_documents_upload.class));
            }
        });
        my_preference_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), program_preference_Activity.class));
            }
        });
        my_transations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), my_transactions_activity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(profile_dashboard.this, MainActivity.class));
        finish();
    }


}