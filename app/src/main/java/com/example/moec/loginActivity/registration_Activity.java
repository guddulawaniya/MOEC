package com.example.moec.loginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class registration_Activity extends AppCompatActivity {

    TextInputEditText firstname,lastname,emailaddress,referralcode,dateofbirth;
    AutoCompleteTextView country,state,city;
    RadioGroup gender;
    TextInputLayout fisrtnamelayout,lastnamelayout,emailaddresslayout,countrylayout,statelayout,citylayout,dateofbirthlayout,
            referralcodelayout;
    RadioButton male,female,other;


    String[] countryList = { "India", "America","Canada","new ZeaLand","Australia","United states","United kingdom" };
    String[] states = { "Andhra Pradesh", "Arunachal Pradesh ","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka"};
    String[] citylist = { "Jaipur", "Mathura","Agra","Pune","Gujrat","Hydrabad","Noida" };
    Button submitbutton;

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

         male = findViewById(R.id.radioButton1);
         female = findViewById(R.id.radioButton2);
         other = findViewById(R.id.radioButton3);

         referralcode = findViewById(R.id.referralcode);
        submitbutton = findViewById(R.id.submitbutton);


         fisrtnamelayout = findViewById(R.id.firstnamelayout);
         lastnamelayout = findViewById(R.id.lastnamelayout);
         emailaddresslayout = findViewById(R.id.emailaddresslayout);
         referralcodelayout = findViewById(R.id.referralcodelayout);
        countrylayout = findViewById(R.id.countrylayout);
        statelayout = findViewById(R.id.statelayout);
        citylayout = findViewById(R.id.citylayout);
        dateofbirthlayout = findViewById(R.id.dateofbirthlayout);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter(this, R.layout.countrylist_layout, countryList);


        country.setAdapter(countryAdapter);
        country.setThreshold(10);
        country.setDropDownBackgroundResource(R.color.background_blue_shadew);
        country.setTextColor(Color.BLACK);
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker().setTheme(R.style.Theme_App);
        materialDateBuilder.setTitleText("Date of Birth");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();



        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateofbirth.setText(materialDatePicker.getHeaderText());

            }
        });

        dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

            }
        });




        ArrayAdapter<String> stateAdapter = new ArrayAdapter(this,  R.layout.countrylist_layout, states);

        state.setThreshold(10);
        state.setAdapter(stateAdapter);
        state.setTextColor(Color.BLACK);
        state.setDropDownBackgroundResource(R.color.background_blue_shadew);


        ArrayAdapter<String> cityadapter = new ArrayAdapter(this,  R.layout.countrylist_layout, citylist);

        city.setThreshold(10);
        city.setAdapter(cityadapter);
        city.setDropDownBackgroundResource(R.color.background_blue_shadew);


        textwatch(firstname,fisrtnamelayout);
        textwatch(lastname,lastnamelayout);
        textwatch(emailaddress,emailaddresslayout);
        textwatch(dateofbirth,dateofbirthlayout);


        textwatcherAutocomplete(country,countrylayout);
        textwatcherAutocomplete(state,statelayout);
        textwatcherAutocomplete(city,citylayout);




        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitbuttondata();
            }
        });



    }



    void submitbuttondata()
    {
        if (!(firstname.getText().toString().isEmpty()) &&
                !(lastname.getText().toString().isEmpty()) && (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) &&
                !(dateofbirth.getText().toString().isEmpty()) && !country.getText().toString().isEmpty() && (male.isChecked() || female.isChecked() || other.isChecked()) &&
                !state.getText().toString().isEmpty()&&
                !city.getText().toString().isEmpty())
        {

            SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
            editor.putString("Fname",firstname.getText().toString());
            editor.putString("Lname",lastname.getText().toString());
            editor.putString("Email",emailaddress.getText().toString());
            editor.putString("DOb",dateofbirth.getText().toString());
            editor.putString("Country",country.getText().toString());
            editor.putString("State",state.getText().toString());
            editor.putString("City",city.getText().toString());


            if (male.isChecked())
            {
                editor.putString("g","male");
            }
            else if (female.isChecked())
            {
                editor.putString("g","female");
            }
            else
            {
                editor.putString("g","other");

            }
            editor.commit();

            Intent intent = new Intent(registration_Activity.this, verify_OTP_Activity.class);
            intent.putExtra("email",emailaddress.getText().toString());
            startActivity(intent);
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
            errorshow(dateofbirthlayout, dateofbirth);
        }
        else if (country.getText().toString().isEmpty()) {
            radiobuttonshowError(countrylayout,country);
        }
        else if (state.getText().toString().isEmpty()) {
            radiobuttonshowError(statelayout,state);
        }
        else if (city.getText().toString().isEmpty()) {
            radiobuttonshowError(citylayout,city);
        }
        else if (!male.isChecked() && !female.isChecked() && !other.isChecked()) {

            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            male.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            female.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            other.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));

        }
    }

    public void errorshow(TextInputLayout layout, TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        layout.setError("Required*");
        text.requestFocus();
    }
    void radiobuttonshowError(TextInputLayout layout,AutoCompleteTextView text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setError("Required*");
        text.requestFocus();
    }


   public void textwatcherAutocomplete( AutoCompleteTextView text,TextInputLayout layout)
    {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                layout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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