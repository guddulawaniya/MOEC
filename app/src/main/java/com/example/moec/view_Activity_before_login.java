package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.smarteist.autoimageslider.SliderView;


public class view_Activity_before_login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);


        CardView nextcirclebutton = findViewById(R.id.nextbuttonprogressbar);
        TextView skiptext = findViewById(R.id.skiptext);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        Button materialNestbbutton = findViewById(R.id.materialNestbbutton);


        SliderView sliderView = findViewById(R.id.slider);

        sliderView.setSliderAdapter(new SliderAdapter());

        nextcirclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_Activity.class));
                finish();
            }
        }); skiptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_Activity.class));
                finish();
            }
        });


    }
}