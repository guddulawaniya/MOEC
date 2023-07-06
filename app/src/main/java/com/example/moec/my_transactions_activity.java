package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class my_transactions_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transactions);


        TextView clearText = findViewById(R.id.cleartext);
        TextView desci = findViewById(R.id.descri_no_found);
        TextView title  = findViewById(R.id.title);
        TextView toolbartitle  = findViewById(R.id.toolbar_title);

        Button nofoundbutton = findViewById(R.id.nofoundbutton);
        ImageView backbutton = findViewById(R.id.backbutton);
        toolbartitle.setText("My Transactions");
        title.setText("No Record Found");
        clearText.setVisibility(View.GONE);
        desci.setText("Your transaction will appear here");

        nofoundbutton.setVisibility(View.GONE);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}