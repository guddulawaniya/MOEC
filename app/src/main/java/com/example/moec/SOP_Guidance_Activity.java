package com.example.moec;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SOP_Guidance_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sop_guidance);



        LinearLayout documentlinear = findViewById(R.id.addmissonlinear);
        documentlinear.setVisibility(View.GONE);
        LinearLayout loanlinear = findViewById(R.id.loanlinear);
        loanlinear.setVisibility(View.GONE);
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView headingtext = findViewById(R.id.headingtext);

        headingtext.setText("SOP Guidance");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}