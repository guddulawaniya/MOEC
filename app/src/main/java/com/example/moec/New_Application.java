package com.example.moec;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class New_Application extends AppCompatActivity {


    String[] selection = {"My Preferred", "Expert Advice",};
    String[] institudelist = {"My Preferred", "Expert Advice",};
    String[] campuslist = {"My Preferred", "Expert Advice",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);


        TextView title = findViewById(R.id.toolbar_title);

        AutoCompleteTextView programSelection = findViewById(R.id.countryselection);
        AutoCompleteTextView institudecomplete = findViewById(R.id.institudeAutocomplete);
      //  AutoCompleteTextView campuscomplete = findViewById(R.id.campusautocamplete);
        AutoCompleteTextView selectcourse = findViewById(R.id.selectcourse);
        AutoCompleteTextView intakeinput = findViewById(R.id.intakeinput);


        Button reviewButton = findViewById(R.id.reviewApplicationButton);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.countrylist_layout, selection);


        programSelection.setThreshold(1);
        programSelection.setAdapter(adapter);
        programSelection.setTextColor(Color.BLACK);
        programSelection.setDropDownBackgroundResource(R.color.background_blue_shadew);


        LinearLayout uploadlinear = findViewById(R.id.uploadlinear);
        ImageView canclebutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);

        canclebutton.setImageResource(R.drawable.close_icon);


        institudecomplete.setThreshold(1);
        institudecomplete.setAdapter(adapter);
        institudecomplete.setTextColor(Color.BLACK);
        institudecomplete.setDropDownBackgroundResource(R.color.background_blue_shadew);

       /* campuscomplete.setThreshold(1);
        campuscomplete.setAdapter(adapter);
        campuscomplete.setTextColor(Color.BLACK);
        campuscomplete.setDropDownBackgroundResource(R.color.background_blue_shadew);*/

        selectcourse.setThreshold(1);
        selectcourse.setAdapter(adapter);
        selectcourse.setTextColor(Color.BLACK);
        selectcourse.setDropDownBackgroundResource(R.color.background_blue_shadew);


        uploadlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), My_documents_upload.class));

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

//    void setintakedate()
//    {
//        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(New_Application.this,
//                new MonthPickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(int selectedMonth, int selectedYear) {  }
//                    }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));
//
//        builder.setActivatedMonth(Calendar.JULY)
//                .setMinYear(2010)
//                .setActivatedYear(2023)
//                .setMaxYear(2030)
//                .setMinMonth(Calendar.FEBRUARY)
//                .setTitle("Select Intake Year")
//                .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
//                // .setMaxMonth(Calendar.OCTOBER)
//                // .setYearRange(1890, 1890)
//                // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
//                //.showMonthOnly()
//                // .showYearOnly()
//                .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
//                    @Override
//                    public void onMonthChanged(int selectedMonth) {  } })
//                .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
//                            @Override
//                            public void onYearChanged(int selectedYear) {  } })
//                .build().show();
//    }

    private void config() {
        findViewById(android.R.id.content).setTransitionName("fab");

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);


    }

//    @Override
//    public void onBackPressed() {
//
//
//        close_button_bottomsheet close_button_bottomsheet = new close_button_bottomsheet();
//        close_button_bottomsheet.show(getSupportFragmentManager(),"alertfragment");
//
//
//
//    }

}