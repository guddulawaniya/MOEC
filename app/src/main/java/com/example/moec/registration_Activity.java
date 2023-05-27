package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Calendar;

public class registration_Activity extends AppCompatActivity {

    TextInputEditText firstname,lastname,emailaddress,referralcode;
    TextView dateofbirth;
    String country;
    TextView state;
    TextView city;
    RadioGroup gender;
    CountryCodePicker codePicker;
    TextInputLayout fisrtnamelayout,lastnamelayout,emailaddresslayout,dateofbirthlayout,countrylayout,statelayout,citylayout,
            referralcodelayout;

    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView changenumber = findViewById(R.id.changenumber);
         firstname = findViewById(R.id.firstname);
         lastname = findViewById(R.id.lastname);
         emailaddress = findViewById(R.id.emailaddress);
         dateofbirth = findViewById(R.id.dateofbirth);
//         country = findViewById(R.id.country);
         state = findViewById(R.id.state);
         city = findViewById(R.id.city);
         gender = findViewById(R.id.gender);
         referralcode = findViewById(R.id.referralcode);
         codePicker = findViewById(R.id.country_code);
        country = codePicker.getTextView_selectedCountry().toString();

         fisrtnamelayout = findViewById(R.id.firstnamelayout);
         lastnamelayout = findViewById(R.id.lastnamelayout);
         emailaddresslayout = findViewById(R.id.emailaddresslayout);
         referralcodelayout = findViewById(R.id.referralcodelayout);

        textwatch(firstname,fisrtnamelayout);
        textwatch(lastname,lastnamelayout);
        textwatch(emailaddress,emailaddresslayout);




        Button submitbutton = findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), greeting_Activity.class));
                finish();

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
                        // on below line we are passing context.
                      registration_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                dateofbirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

//        country.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String country_code=codePicker.getSelectedCountryName();
//                country.setText(country_code);
//
//            }
//        });
        changenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_Activity.class));
                finish();
            }
        }); backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }




    void submitbuttondata()
    {
        if (!(firstname.getText().toString().isEmpty()) &&
                !(lastname.getText().toString().isEmpty()) &&
                !(Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) &&
                !(dateofbirth.getText().toString().isEmpty()) &&
                !(country.isEmpty()))

        {
            startActivity(new Intent(getApplicationContext(), greeting_Activity.class));
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
        else if (country.isEmpty()) {
            codePicker.setAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));

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