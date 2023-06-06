package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class program_preference_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_preference);
        getSupportActionBar().hide();
        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView clearbutton = findViewById(R.id.cleartext);
        toolbartitle.setText("Preferences");
        clearbutton.setVisibility(View.GONE);
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}