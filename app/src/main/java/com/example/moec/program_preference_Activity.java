package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class program_preference_Activity extends AppCompatActivity {



    Timer mytimer;
    TextView selectcountry,selectEducation,selectCourse,selectTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_preference);

        // finding ids Autocomplete
        selectcountry = findViewById(R.id.selectcountry);
        selectEducation = findViewById(R.id.selectEducation);
        selectCourse = findViewById(R.id.selectCourse);
        selectTest = findViewById(R.id.selectTest);

        SharedPreferences preferences = getSharedPreferences("preference",MODE_PRIVATE);


        mytimer = new Timer();
        mytimer.schedule(new TimerTask() {
            @Override
            public void run() {

              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {

                      String countryname = preferences.getString("countryname",null);
                      String interest = preferences.getString("interest",null);
                      String Education = preferences.getString("educationtext",null);
                      String percentage = preferences.getString("percentage",null);
                      String englishtest = preferences.getString("examname",null);


                      selectcountry.setText(countryname);
                      selectEducation.setText(interest);
                      selectCourse.setText(Education+" - "+percentage);
                      selectTest.setText(englishtest);
                  }
              });

            }
        },0,1000);




        // finding the ids Textview toolbar
        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView clearbutton = findViewById(R.id.cleartext);

        // set the text on toolbar textview
        toolbartitle.setText("Preferences");
        clearbutton.setVisibility(View.GONE);



        selectcountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(program_preference_Activity.this,Preference_update_Activity.class);
                intent.putExtra("layoutid",1);
                intent.putExtra("title","Want to study in");
                startActivity(intent);


            }
        });
        selectEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(program_preference_Activity.this,Preference_update_Activity.class);
                intent.putExtra("layoutid",2);
                intent.putExtra("title","Area of interest");
                startActivity(intent);


            }
        });
        selectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(program_preference_Activity.this,Preference_update_Activity.class);
                intent.putExtra("layoutid",3);
                intent.putExtra("title","Education Qualification");
                startActivity(intent);


            }
        });
        selectTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(program_preference_Activity.this,Preference_update_Activity.class);
                intent.putExtra("layoutid",4);
                intent.putExtra("title","English Test Score");
                startActivity(intent);


            }
        });





        // backbutton
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}