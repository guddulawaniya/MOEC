package com.example.moec.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.config;
import com.example.moec.MainActivity;
import com.example.moec.R;
import com.example.moec.loginActivity.login_Activity;
import com.example.moec.loginActivity.registration_Activity;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import kotlin.text.Charsets;

public class Edit_fragment extends DialogFragment {

    public static TextInputEditText firstname, lastname, pincode, city, state, country ;

    AutoCompleteTextView gender,maritalstatus;
    String[] genderlist = {"Male", "Female", "Others"};
    String[] marital = {"Single", "Married"};

    MaterialTextView dob;
    ProgressBar progressBar;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    TextInputLayout pincodelayout;
    String userid;
    String number;
    Button updatebutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile_, container, false);

        ImageView closebutton = view.findViewById(R.id.closefragmentbutton);
         updatebutton = view.findViewById(R.id.updatebutton);
        progressBar = view.findViewById(R.id.pincodeprogressbar);
        pincodelayout = view.findViewById(R.id.pincodelayout);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setIcon(R.drawable.logo_symbol_colour);
        progressDialog.setTitle("Update Profile");
        progressDialog.setMessage("Please Wait..");


        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update Profile");


        firstname = view.findViewById(R.id.firstname);
        lastname = view.findViewById(R.id.lastname);
        dob = view.findViewById(R.id.dateofbirth);
        gender = view.findViewById(R.id.gender);
        pincode = view.findViewById(R.id.pincode);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        country = view.findViewById(R.id.country);
        maritalstatus = view.findViewById(R.id.maritalstatus);




        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatefunction();

            }
        });


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker().setTheme(R.style.Theme_App);
        materialDateBuilder.setTitleText("Date of Birth");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        ArrayAdapter<String> genderlistautocomeplete = new ArrayAdapter(getContext(), R.layout.countrylist_layout, genderlist);

        ArrayAdapter<String> maritallist = new ArrayAdapter(getContext(), R.layout.countrylist_layout, marital);

        maritalstatus.setThreshold(10);
        maritalstatus.setAdapter(maritallist);
        maritalstatus.setTextColor(Color.BLACK);
        maritalstatus.setDropDownBackgroundResource(R.color.background_blue_shadew);



        gender.setAdapter(genderlistautocomeplete);
        gender.setThreshold(10);
        gender.setDropDownBackgroundResource(R.color.background_blue_shadew);
        gender.setTextColor(Color.BLACK);


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {

                dob.setText(materialDatePicker.getHeaderText());

            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getChildFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });
        InternetConnection nt = new InternetConnection(getContext());


        if (!pincode.getText().toString().isEmpty() ) {

            if (nt.isConnected())
            {
                fetchAddressdata(pincode.getText().toString());
            }
            else {
                Toast.makeText(getContext(), "Please Your  Internet Connectiion ", Toast.LENGTH_SHORT).show();
            }

        }




        SharedPreferences preferences = getContext().getSharedPreferences("registrationform", MODE_PRIVATE);
        String getfirstname = preferences.getString("Fname", null);
        String getlastname = preferences.getString("Lname", null);
         number = preferences.getString("number", null);
        String getdob = preferences.getString("DOb", null);
        String getgender = preferences.getString("g", "");
        String getpincode = preferences.getString("pincode", "");
        String getcountry = preferences.getString("country","");
        String getstate = preferences.getString("state","");
        String getcity = preferences.getString("city","");
        String getmaritalstatus = preferences.getString("marital","");
        String qualification = preferences.getString("qualification","");
        userid = preferences.getString("userid","");

        fetchAddressdata(getpincode);

        firstname.setText(getfirstname);
        lastname.setText(getlastname);
        dob.setText(getdob);
        gender.setText(getgender);
        pincode.setText(getpincode);
        country.setText(getcountry);
        state.setText(getstate);
        city.setText(getcity);
        maritalstatus.setText(getmaritalstatus);

        updatebutton.setEnabled(false);

        pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    fetchAddressdata(pincode.getText().toString());
                    pincodelayout.setErrorEnabled(false);

                }
                if (pincode.length() == 6) {
                    pincodelayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                    fetchAddressdata(pincode.getText().toString());


                } else if (pincode.length() < 6) {
                    pincodelayout.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }
                pincodelayout.setHelperText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            // Set the dialog's dimensions for full-screen
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setLayout(width, height);
        }
    }

    void updatefunction() {
        progressDialog.show();

        String getfirstname = firstname.getText().toString();
        String getlastname = lastname.getText().toString();
        String getdob = dob.getText().toString();
        String getgender = gender.getText().toString();
        String getpincode = pincode.getText().toString();
        String getcountry = country.getText().toString();
        String getstate = state.getText().toString();
        String getcity = city.getText().toString();
        String getmaritalstatus = maritalstatus.getText().toString();

        String registrationURL = config.Base_url + "updateStudentProfileApi?" +
                "user_id=" + userid +
                "&firstname=" + getfirstname +
                "&lastname=" + getlastname+
                "&date_of_birth=" + getdob+
                "&country_code=" +
                "&marital_status=" + getmaritalstatus+
                "&gender=" + getgender+
                "&phone_no=" + number+
                "&mailing_country=" + getcountry+
                "&mailing_state=" + getstate+
                "&mailing_city=" + getcity+
                "&mailing_pincode=" + getpincode+
                "&mailing_address=" + getcity+","+ getstate+","+ getcountry+","+getpincode;

        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    String status = obj.getString("success");

                    if (status.equals("true")) {
                        progressDialog.dismiss();
                        SharedPreferences.Editor editor = getContext().getSharedPreferences("registrationform", MODE_PRIVATE).edit();
                        editor.putString("Fname",firstname.getText().toString());
                        editor.putString("Lname",lastname.getText().toString());
                        editor.putString("number",number);
                        editor.putString("DOb",dob.getText().toString());
                        editor.putString("g",gender.getText().toString());
                        editor.putString("pincode",pincode.getText().toString());
                        editor.putString("country",country.getText().toString());
                        editor.putString("state",state.getText().toString());
                        editor.putString("city",city.getText().toString());
                        editor.putString("marital",maritalstatus.getText().toString());
                        editor.commit();

                        Toast.makeText(getContext(), "Successfully updated details", Toast.LENGTH_SHORT).show();

                        dismiss();
                        getActivity().recreate();


                    } else {
                        Toast.makeText(getContext(), "failed" + obj, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

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
        obj.execute(registrationURL);

    }


    private void fetchAddressdata(String pincode) {
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://api.postalpincode.in/pincode/" + pincode;


        class registration extends AsyncTask<String, String, String> {

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONArray array = new JSONArray(s);
                    JSONObject obj = array.getJSONObject(0);
                    String status = obj.getString("Status");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("registrationform", MODE_PRIVATE).edit();

                    if (status.equals("Success")) {
                        progressBar.setVisibility(View.GONE);

                        JSONArray postdata = obj.getJSONArray("PostOffice");
                        JSONObject dataobject = postdata.getJSONObject(1);

                        String district = dataobject.getString("District");
                        String States = dataobject.getString("State");
                        String Countries = dataobject.getString("Country");

                        editor.putString("country", Countries);
                        editor.putString("pincode", pincode);
                        editor.putString("state", States);
                        editor.putString("city", district);
                        editor.commit();
                        country.setText(Countries);
                        state.setText(States);
                        city.setText(district);

                        updatebutton.setEnabled(true);


                    } else {
                        progressBar.setVisibility(View.GONE);
                        country.setText("");
                        state.setText("");
                        city.setText("");

                        editor.commit();
                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
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
}