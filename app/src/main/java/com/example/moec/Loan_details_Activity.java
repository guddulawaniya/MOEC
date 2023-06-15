package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Loan_details_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);


        ImageView backbutton = findViewById(R.id.backbutton);
        TextView headingtext = findViewById(R.id.headingtext);

        LinearLayout documentlinear = findViewById(R.id.documentlinear);
        documentlinear.setVisibility(View.GONE);

        headingtext.setText("Loan Details");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}