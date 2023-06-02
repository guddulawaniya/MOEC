package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Favorate_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorate);
        getSupportActionBar().hide();

        ImageView backbutton = findViewById(R.id.backbutton);
        Button explorebutton = findViewById(R.id.nofoundbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        TextView descri_no_found = findViewById(R.id.descri_no_found);

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        toolbartitle.setText("Shortlisted Program");
        explorebutton.setText("Explore Programs");
        descri_no_found.setText("The list of shortlisted programs will be displayed on this page");
        explorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), All_Programs_fragment.class));
            }
        });
    }
}