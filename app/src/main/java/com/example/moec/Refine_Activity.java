package com.example.moec;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.RangeSlider;

public class Refine_Activity extends AppCompatActivity {


    String typeinstitude,work_permit,duration;
    AutoCompleteTextView country,state,institude,studylevel,programtype,intakedate,englishcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine);


        RangeSlider tuitionfee_slider = findViewById(R.id.tuition_rangslider);
        TextView tuitionfee_rang_text = findViewById(R.id.tuitionfee_rang_text);
        RangeSlider appliation_rang_slider = findViewById(R.id.appliation_rang_slider);
        TextView appliation_rang_slider_text = findViewById(R.id.appliation_rang_slider_text);
        ChipGroup institude_Type_group = findViewById(R.id.institude_Type_group);
        ChipGroup work_permit_group = findViewById(R.id.work_permit_group);
        ChipGroup duration_group = findViewById(R.id.duration_group);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        institude = findViewById(R.id.institude);
        studylevel = findViewById(R.id.studylevel);
        programtype = findViewById(R.id.programtype);
        intakedate = findViewById(R.id.intakedate);
        englishcourse = findViewById(R.id.englishcourse);


        institude_Type_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                Chip selectedChip = group.findViewById(checkedId);
                if (selectedChip != null) {
                    typeinstitude = selectedChip.getText().toString();


                    Toast.makeText(Refine_Activity.this, "Selected: " + typeinstitude, Toast.LENGTH_SHORT).show();
                }
            }
        });
        work_permit_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                Chip selectedChip = group.findViewById(checkedId);
                if (selectedChip != null) {
                    work_permit = selectedChip.getText().toString();

                    Toast.makeText(Refine_Activity.this, "Selected: " + work_permit, Toast.LENGTH_SHORT).show();
                }
            }
        });
        duration_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                Chip selectedChip = group.findViewById(checkedId);
                if (selectedChip != null) {
                    duration = selectedChip.getText().toString();

                    Toast.makeText(Refine_Activity.this, "Selected: " + duration, Toast.LENGTH_SHORT).show();
                }
            }
        });





    tuitionfee_slider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            tuitionfee_rang_text.setText(slider.getValues().toString());


        }

        @SuppressLint("RestrictedApi")
        @Override
        public void onStopTrackingTouch(@NonNull RangeSlider slider) {


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