package com.example.moec;

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

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}