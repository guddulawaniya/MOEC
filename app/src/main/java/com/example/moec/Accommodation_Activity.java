package com.example.moec;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accommodation_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView headingtext = findViewById(R.id.headingtext);

        LinearLayout documentlinear = findViewById(R.id.documentlinear);
        documentlinear.setVisibility(View.GONE);
        LinearLayout loanlinear = findViewById(R.id.loanlinear);
        loanlinear.setVisibility(View.GONE);

        headingtext.setText("Accomodation");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}