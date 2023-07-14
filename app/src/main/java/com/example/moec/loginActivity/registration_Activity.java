package com.example.moec.loginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.R;
import com.example.moec.findAddress_by_pincode;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class registration_Activity extends AppCompatActivity {

    TextInputEditText firstname,lastname,mobilenumber,emailaddress,pincode;
    MaterialTextView dateofbirth;
    AutoCompleteTextView gender,courselevel;
    TextInputLayout fisrtnamelayout,lastnamelayout,mobilenumberlayout,genderlayout,pincodelayout, courselevellayout,emailaddresslayout;


    String[] genderlist = { "Male", "Female","Others" };

    String[] courselevellist = { "Intermediate", "Graduate", "Master Degree", };
    Button submitbutton;
    TextView toolbar_title,cleartext;
    ImageView backbutton;
    boolean check=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        mobilenumber = findViewById(R.id.mobilenumber);
        dateofbirth = findViewById(R.id.dateofbirth);
        gender = findViewById(R.id.gender);
        pincode = findViewById(R.id.pincode);
        courselevel = findViewById(R.id.courselevel);
        emailaddress = findViewById(R.id.emailaddress);
        backbutton = findViewById(R.id.backbutton);

        submitbutton = findViewById(R.id.submitbutton);
        toolbar_title = findViewById(R.id.toolbar_title);
        cleartext = findViewById(R.id.cleartext);
        toolbar_title.setText("Registartion");
        cleartext.setVisibility(View.GONE);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        fisrtnamelayout = findViewById(R.id.firstnamelayout);
        emailaddresslayout = findViewById(R.id.emailaddresslayout);
        lastnamelayout = findViewById(R.id.lastnamelayout);
        mobilenumberlayout = findViewById(R.id.mobilenumberlayout);
        courselevellayout = findViewById(R.id.courselevellayout);
        genderlayout = findViewById(R.id.genderlayout);
        pincodelayout = findViewById(R.id.pincodelayout);




        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        firstname.setText(preferences.getString("Fname",null));
        lastname.setText(preferences.getString("Lname",null));
        mobilenumber.setText(preferences.getString("number",null));
        dateofbirth.setText(preferences.getString("DOb",null));
        gender.setText(preferences.getString("g",null));
        pincode.setText(preferences.getString("pincode",null));
        emailaddress.setText(preferences.getString("email",null));
        courselevel.setText(preferences.getString("qualification",null));

        if (pincode!=null)
        {
            fetchAddressdata(pincode.getText().toString());

        }
        if (emailaddress!=null)
        {
            if (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches())
            {
                emailaddresslayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                emailaddresslayout.setEndIconDrawable(R.drawable.baseline_done_icon_24);

            }

        }




        ArrayAdapter<String> genderlistautocomeplete = new ArrayAdapter(this, R.layout.countrylist_layout, genderlist);


        gender.setAdapter(genderlistautocomeplete);
        gender.setThreshold(10);
        gender.setDropDownBackgroundResource(R.color.background_blue_shadew);
        gender.setTextColor(Color.BLACK);


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


        pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0)
                {
                    pincodelayout.setErrorEnabled(false);

                } else if (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) {
                    pincodelayout.setErrorEnabled(false);
                }

                if (pincode.length()==6)
                {
                    pincodelayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                    fetchAddressdata(pincode.getText().toString());

                }
                else if(pincode.length()<6)
                {
                    pincodelayout.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }
                pincodelayout.setHelperText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        emailaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches())
                {
                    emailaddresslayout.setErrorEnabled(false);
                    emailaddresslayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);

                    emailaddresslayout.setEndIconDrawable(R.drawable.baseline_done_icon_24);

                } else if (charSequence.length()>0) {

                    emailaddresslayout.setErrorEnabled(false);
                    emailaddresslayout.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ArrayAdapter<String> courselevellistadapter = new ArrayAdapter(this,  R.layout.countrylist_layout, courselevellist);

        courselevel.setThreshold(10);
        courselevel.setAdapter(courselevellistadapter);
        courselevel.setTextColor(Color.BLACK);
        courselevel.setDropDownBackgroundResource(R.color.background_blue_shadew);


        textwatch(firstname,fisrtnamelayout);
        textwatch(lastname,lastnamelayout);
        textwatch(mobilenumber,mobilenumberlayout);
        textwatch(emailaddress,emailaddresslayout);


        textwatcherAutocomplete(courselevel,courselevellayout);
        textwatcherAutocomplete(gender,genderlayout);




        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitbuttondata();
            }
        });


    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(registration_Activity.this, login_Activity.class));
        finish();
    }



    void submitbuttondata()
    {
        if (!(firstname.getText().toString().isEmpty()) && !(lastname.getText().toString().isEmpty()) && !(mobilenumber.getText().toString().isEmpty())&&
                !(emailaddress.getText().toString().isEmpty()) && (Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) &&
                !(dateofbirth.getText().toString().isEmpty()) && !(gender.getText().toString().isEmpty()) &&
                !(pincode.getText().toString().isEmpty()) && !(courselevel.getText().toString().isEmpty()))
        {

            if (check)
            {
                SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                editor.putString("Fname",firstname.getText().toString());
                editor.putString("Lname",lastname.getText().toString());
                editor.putString("number",mobilenumber.getText().toString());
                editor.putString("DOb",dateofbirth.getText().toString());
                editor.putString("email",emailaddress.getText().toString());
                editor.putString("g",gender.getText().toString());
                editor.putString("pincode",pincode.getText().toString());
                editor.putString("qualification",courselevel.getText().toString());
                editor.commit();

                Intent intent = new Intent(registration_Activity.this, verify_OTP_Activity.class);
                intent.putExtra("number",mobilenumber.getText().toString());
                startActivity(intent);
            }
            else  validatepincode();



        } else if (firstname.getText().toString().isEmpty()) {
            errorshow(fisrtnamelayout, firstname);

        }
        else if (lastname.getText().toString().isEmpty()) {
            errorshow(lastnamelayout, lastname);

        }
        else if (mobilenumber.getText().toString().isEmpty()) {
            errorshow(mobilenumberlayout, mobilenumber);

        }
        else if (emailaddress.getText().toString().isEmpty()) {
            errorshow(emailaddresslayout, emailaddress);
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText().toString()).matches()) {
            errorshow(emailaddresslayout, emailaddress);
        }
        else if (dateofbirth.getText().toString().isEmpty()) {
            dateofbirth.setError("Select Dob");
        }
        else if (gender.getText().toString().isEmpty()) {
            radiobuttonshowError(genderlayout,gender);
        }
        else if (pincode.getText().toString().isEmpty()) {
            errorshow(pincodelayout, pincode);
        }
        else if (courselevel.getText().toString().isEmpty()) {
            radiobuttonshowError(courselevellayout,courselevel);
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
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
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

    public void fetchAddressdata(String pincode) {

        String url = "https://api.postalpincode.in/pincode/"+pincode;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONArray array = new JSONArray(s);
                    JSONObject obj = array.getJSONObject(0);
                    String status = obj.getString("Status");
                    SharedPreferences.Editor editor = getSharedPreferences("registrationform",MODE_PRIVATE).edit();

                    if (status.equals("Success")) {

                        JSONArray postdata = obj.getJSONArray("PostOffice");
                        JSONObject dataobject = postdata.getJSONObject(1);

                        String district = dataobject.getString("District");
                        String States = dataobject.getString("State");
                        String Countries = dataobject.getString("Country");

                        editor.putString("Country",Countries);
                        editor.putString("State",States);
                        editor.putString("City",district);
                        editor.commit();

                        check=true;
                        rightpin(district,States,Countries);

                    }
                    else
                    {

                        check=false;
                        validatepincode();
                        editor.commit();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... param) {


                try {
                    URL url = new URL(param[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }
        }
        registration obj = new registration();
        obj.execute(url);

    }
    public void validatepincode()
    {
        pincodelayout.startAnimation(AnimationUtils.loadAnimation(registration_Activity.this,R.anim.shake_text));
        pincodelayout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        pincodelayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        pincodelayout.setError("Invalid Pincode");
        pincode.requestFocus();
    }

    void rightpin(String district, String state,String country)
    {

        pincodelayout.setEndIconDrawable(R.drawable.baseline_done_icon_24);
        pincodelayout.setHelperTextColor(ColorStateList.valueOf(Color.GRAY));
        pincodelayout.setHelperText("District : "+district+", State : "+state + ", Country : "+country);
    }


}

