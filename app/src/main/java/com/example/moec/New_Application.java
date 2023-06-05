package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class New_Application extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);


        getSupportActionBar().hide();
        TextView title = findViewById(R.id.toolbar_title);
        ImageView canclebutton = findViewById(R.id.backbutton);


        canclebutton.setImageResource(R.drawable.close_icon);



        canclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             onBackPressed();
            }
        });


        title.setText("New Application");
    }

    @Override
    public void onBackPressed() {

        close_button_bottomsheet close_button_bottomsheet = new close_button_bottomsheet();
        close_button_bottomsheet.show(getSupportFragmentManager(),"alertfragment");

    }
}