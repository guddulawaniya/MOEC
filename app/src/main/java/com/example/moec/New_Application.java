package com.example.moec;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.BottomSheets.close_button_bottomsheet;

public class New_Application extends AppCompatActivity {



    String[] selection = { "My Preferred", "Expert Advice", };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);


        TextView title = findViewById(R.id.toolbar_title);
        AutoCompleteTextView programSelection = findViewById(R.id.programSelection);
        LinearLayout uploadlinear = findViewById(R.id.uploadlinear);
        ImageView canclebutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);

        canclebutton.setImageResource(R.drawable.close_icon);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, selection);


        programSelection.setThreshold(1);

        // Set the adapter for data as a list
        programSelection.setAdapter(adapter);
        programSelection.setTextColor(Color.BLACK);
        programSelection.setDropDownBackgroundResource(R.color.background_blue_shadew);


        uploadlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), New_Application.class));

            }
        });



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