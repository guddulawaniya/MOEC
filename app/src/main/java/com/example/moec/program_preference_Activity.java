package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class program_preference_Activity extends AppCompatActivity {



    TextView selectcountry,selectEducation,selectCourse,selectTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_preference);

        // hide Action bar
        getSupportActionBar().hide();

        // finding the ids Textview toolbar
        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView clearbutton = findViewById(R.id.cleartext);

        // set the text on toolbar textview
        toolbartitle.setText("Preferences");
        clearbutton.setVisibility(View.GONE);

        // finding ids Autocomplete
        selectcountry = findViewById(R.id.selectcountry);
        selectEducation = findViewById(R.id.selectEducation);
        selectCourse = findViewById(R.id.selectCourse);
        selectTest = findViewById(R.id.selectTest);

        selectcountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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