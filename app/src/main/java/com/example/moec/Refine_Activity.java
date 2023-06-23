package com.example.moec;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.RangeSlider;

public class Refine_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine);


        RangeSlider tuitionfee_slider = findViewById(R.id.tuition_rangslider);
        TextView tuitionfee_rang_text = findViewById(R.id.tuitionfee_rang_text);

        RangeSlider appliation_rang_slider = findViewById(R.id.appliation_rang_slider);

        TextView appliation_rang_slider_text = findViewById(R.id.appliation_rang_slider_text);

    tuitionfee_slider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onStartTrackingTouch(@NonNull RangeSlider slider) {




            tuitionfee_rang_text.setText(slider.getValues().toString());
        }

        @SuppressLint("RestrictedApi")
        @Override
        public void onStopTrackingTouch(@NonNull RangeSlider slider) {

            tuitionfee_rang_text.setText(slider.getValues().toString());
        }
    });

    tuitionfee_slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

        }
    });


        appliation_rang_slider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onStartTrackingTouch(@NonNull RangeSlider slider) {




            appliation_rang_slider_text.setText(slider.getValues().toString());
        }

        @SuppressLint("RestrictedApi")
        @Override
        public void onStopTrackingTouch(@NonNull RangeSlider slider) {

            appliation_rang_slider_text.setText(slider.getValues().toString());
        }
    });

        appliation_rang_slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {

        }
    });

        TextView title = findViewById(R.id.toolbar_title);
        TextView actionbutton = findViewById(R.id.cleartext);
        ImageView backbutton = findViewById(R.id.backbutton);


        title.setText("Refine Results");

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }
}