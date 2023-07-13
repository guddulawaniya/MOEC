package com.example.moec;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class SOP_Guidance_Activity extends AppCompatActivity {



    TextInputEditText firstname,lastname,emailid,intakedate;
    TextInputLayout firstnamelayout,lastlayout,emailidlayout,countryfromlayout,
            countrywherelayout,statelayout,citylayout,intakelayout,addmissionstatuslayout;
    AutoCompleteTextView countywhere,countryfrom,addmission,state,city;
    EditText mobilenumber , loanAmount;
    TextView attachFile;
    Button submitbutton;
    String[] countryList = { "India", "America","Canada","new ZeaLand","Australia","United states","United kingdom" };
    String[] states = { "Andhra Pradesh", "Arunachal Pradesh ","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka"};
    String[] Addmisionlist = { "Already on Campus", "Received Offer letter","Waiting Decesion","Waitilisted","Yet to Apply"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config();
        setContentView(R.layout.activity_sop_guidance);


        // input fields textEdit ids
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        emailid = findViewById(R.id.emailaddress);
        intakedate = findViewById(R.id.intakedate);



        // textfields and autocomplete layout ids
        firstnamelayout = findViewById(R.id.firstnamelayout);
        lastlayout = findViewById(R.id.lastnamelayout);
        emailidlayout = findViewById(R.id.emailaddresslayout);
        countryfromlayout = findViewById(R.id.countrylayout);
        countrywherelayout = findViewById(R.id.countrywherelayout);
        statelayout = findViewById(R.id.statelayout);
        citylayout = findViewById(R.id.citylayout);
        intakelayout = findViewById(R.id.intakelayout);
        addmissionstatuslayout = findViewById(R.id.addmissionstatuslayout);
        attachFile = findViewById(R.id.attachFile);


        // autocomplete ids
        countywhere = findViewById(R.id.countrywhere);
        countryfrom = findViewById(R.id.countryfrom);
        addmission = findViewById(R.id.addmissionstatus);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);

        // Editext ids
        mobilenumber = findViewById(R.id.mobilenumber);
        loanAmount = findViewById(R.id.loanamount);

        submitbutton = findViewById(R.id.submitbutton);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter(this, R.layout.countrylist_layout, countryList);
         ArrayAdapter<String> stateAdapter = new ArrayAdapter(this, R.layout.countrylist_layout, states);
         ArrayAdapter<String> AddmisionAdapter = new ArrayAdapter(this, R.layout.countrylist_layout, Addmisionlist);

        countywhere.setAdapter(countryAdapter);
        countywhere.setThreshold(10);
        countywhere.setDropDownBackgroundResource(R.color.background_blue_shadew);
        countywhere.setTextColor(Color.BLACK);

        countryfrom.setAdapter(countryAdapter);
        countryfrom.setThreshold(10);
        countryfrom.setDropDownBackgroundResource(R.color.background_blue_shadew);
        countryfrom.setTextColor(Color.BLACK);

        city.setAdapter(stateAdapter);
        city.setThreshold(10);
        city.setDropDownBackgroundResource(R.color.background_blue_shadew);
        city.setTextColor(Color.BLACK);

        addmission.setAdapter(AddmisionAdapter);
        addmission.setThreshold(10);
        addmission.setDropDownBackgroundResource(R.color.background_blue_shadew);
        addmission.setTextColor(Color.BLACK);

        state.setAdapter(stateAdapter);
        state.setThreshold(10);
        state.setDropDownBackgroundResource(R.color.background_blue_shadew);
        state.setTextColor(Color.BLACK);


        // autocomplete  textwatcher
        textwatcherAutocomplete(countryfrom,countryfromlayout);
        textwatcherAutocomplete(countywhere,countrywherelayout);
        textwatcherAutocomplete(state,statelayout);
        textwatcherAutocomplete(city,citylayout);


        textwatch(firstname, firstnamelayout);
        textwatch(lastname, lastlayout);
        textwatch(emailid, emailidlayout);


        attachFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);
            }
        });




        LinearLayout documentlinear = findViewById(R.id.addmissonlinear);
        documentlinear.setVisibility(View.GONE);
        LinearLayout loanlinear = findViewById(R.id.loanlinear);
        loanlinear.setVisibility(View.GONE);
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView headingtext = findViewById(R.id.headingtext);

        headingtext.setText("SOP Guidance");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });


    }



    void validation(){

        String Fname = firstname.getText().toString();
        String Lname = lastname.getText().toString();
        String email = emailid.getText().toString();
        String mobile = mobilenumber.getText().toString();
        String countryF = countryfrom.getText().toString();
        String countryW = countywhere.getText().toString();
        String statetext = state.getText().toString();
        String citytext = city.getText().toString();



        if (Fname.isEmpty())
        {
            errorshow(firstnamelayout,firstname);

        } else if (Lname.isEmpty()) {
            errorshow(lastlayout,lastname);

        }
        else if (email.isEmpty()) {
            errorshow(emailidlayout,emailid);

        }
        else if (mobile.isEmpty()) {

            mobilenumber.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            mobilenumber.setError("Required*");
            mobilenumber.requestFocus();

        }
        else if (countryF.isEmpty()) {

            AutocompleteError(countryfromlayout,countryfrom);

        }
        else if (countryW.isEmpty()) {

            AutocompleteError(countrywherelayout,countywhere);

        }
        else if (statetext.isEmpty()) {

            AutocompleteError(statelayout,state);

        }
        else if (citytext.isEmpty()) {

            AutocompleteError(citylayout,city);

        }
        else if (attachFile.getText().toString().isEmpty()) {

            attachFile.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            attachFile.setError("Required*");

        }
        else {
            Toast.makeText(this, "Successfully Submit data", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    void errorshow(TextInputLayout layout,TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
        layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
        layout.setError("Required*");
        text.requestFocus();
    }
    void AutocompleteError(TextInputLayout layout,AutoCompleteTextView text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
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

                }
                else if (Patterns.EMAIL_ADDRESS.matcher(emailid.getText().toString()).matches()) {
                    layout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void config() {
        findViewById(android.R.id.content).setTransitionName("fab");

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);



    }
}