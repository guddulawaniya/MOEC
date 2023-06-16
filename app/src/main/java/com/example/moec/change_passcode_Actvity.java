package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class change_passcode_Actvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passcode_actvity);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView toolbartitle  = findViewById(R.id.toolbar_title);
        toolbartitle.setText("Change Passcode");
        cleartext.setVisibility(View.GONE);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}