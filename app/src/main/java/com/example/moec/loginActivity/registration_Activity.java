package com.example.moec.loginActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.R;
import com.example.moec.greeting_Activity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class registration_Activity extends AppCompatActivity {

    TextInputEditText firstname,lastname,emailaddress,referralcode;
    AutoCompleteTextView country,state,city;
    RadioGroup gender;
    TextView codePicker,dateofbirth;
    TextInputLayout fisrtnamelayout,lastnamelayout,emailaddresslayout,
            referralcodelayout;


    String[] countryList = { "India", "America","Canada","new ZeaLand","Australia","United states","United kingdom" };
    String[] states = { "Utter Pradesh", "Rajsthan ","Delhi","Bihar","Australia","United states","United kingdom" };
    String[] citylist = { "Jaipur", "Mathura","Agra","Pune","Gujrat","Hydrabad","Noida" };
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         firstname = findViewById(R.id.firstname);
         lastname = findViewById(R.id.lastname);
         emailaddress = findViewById(R.id.emailaddress);
         dateofbirth = findViewById(R.id.dateofbirth);
         country = findViewById(R.id.country);
         state = findViewById(R.id.state);
         city = findViewById(R.id.city);
         gender = findViewById(R.id.gender);
         referralcode = findViewById(R.id.referralcode);

         fisrtnamelayout = findViewById(R.id.firstnamelayout);
         lastnamelayout = findViewById(R.id.lastnamelayout);
         emailaddresslayout = findViewById(R.id.emailaddresslayout);
         referralcodelayout = findViewById(R.id.referralcodelayout);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, countryList);


        country.setAdapter(countryAdapter);
        country.setDropDownBackgroundResource(R.color.background_blue_shadew);






        ArrayAdapter<String> stateAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, states);

        state.setThreshold(1);
        state.setAdapter(stateAdapter);
        state.setTextColor(Color.BLACK);
        state.setDropDownBackgroundResource(R.color.background_blue_shadew);


        ArrayAdapter<String> cityadapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, citylist);

        city.setThreshold(1);
        city.setAdapter(cityadapter);
        city.setDropDownBackgroundResource(R.color.background_blue_shadew);


        textwatch(firstname,fisrtnamelayout);
        textwatch(lastname,lastnamelayout);
        textwatch(emailaddress,emailaddresslayout);
        Dialog dialog = new Dialog(getApplicationContext());


        Button submitbutton = findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitbuttondata();
            }
        });

        dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 calendar = Calendar.getInstance();
                 year = calendar.get(Calendar.YEAR);
                 month = calendar.get(Calendar.MONTH);
                 day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(

                      registration_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateofbirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },

                        year, month, day);

                datePickerDialog.show();
            }
        });

    }




    void submitbuttondata()
    {
        if (!(firstname.getText().toString().isEmpty()) &&
                !(lastname.getText().toString().isEmpty()) && (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) &&
                !(dateofbirth.getText().toString().isEmpty()))

        {
            startActivity(new Intent(getApplicationContext(), verify_OTP_Activity.class));
            finish();


        } else if (firstname.getText().toString().isEmpty()) {
            errorshow(fisrtnamelayout, firstname);

        }
        else if (lastname.getText().toString().isEmpty()) {
            errorshow(lastnamelayout, lastname);

        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches())) {
            errorshow(emailaddresslayout, emailaddress);

        }
        else if (dateofbirth.getText().toString().isEmpty()) {
            dateofbirth.setError("Required*");
            dateofbirth.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));




        }

    }

    void errorshow(TextInputLayout layout,TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setError("Required*");
        text.requestFocus();
    }

    void textwatch(TextInputEditText text, TextInputLayout layout)
    {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0)
                {
                    layout.setErrorEnabled(false);

                } else if (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) {
                    layout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}