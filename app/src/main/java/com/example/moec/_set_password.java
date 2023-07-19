package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.moec.loginActivity.registration_Activity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class _set_password extends AppCompatActivity {

    TextInputEditText setpassword, comfirmpassword;
    TextInputLayout setpasswordlayout, comfirmpasswordlayout;
    CheckBox passcheck;
    TextView notmatchedtext;
    Button submitbutton;

    String URL = "http://192.168.1.69/API/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        setpassword = findViewById(R.id.setpassword);
        comfirmpassword = findViewById(R.id.comfirmpassword);
        //layout
        setpasswordlayout = findViewById(R.id.setpasswordlayout);
        comfirmpasswordlayout = findViewById(R.id.comfirmpasswordlayout);
        passcheck = findViewById(R.id.passcheck);
        notmatchedtext = findViewById(R.id.notmatchedtext);
        submitbutton = findViewById(R.id.submitbutton);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();

            }
        });

        textwatcher(setpassword,setpasswordlayout);
        textwatcher(comfirmpassword,comfirmpasswordlayout);


    }

    void validation() {
        if (!setpassword.getText().toString().isEmpty() && !comfirmpassword.getText().toString().isEmpty()) {
            if (setpassword.getText().toString().equals(comfirmpassword.getText().toString())) {
                if (passcheck.isChecked()) {

                    SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                    editor.putString("password", setpassword.getText().toString());
                    editor.commit();


                    startActivity(new Intent(_set_password.this, greeting_Activity.class));
                    overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                    finish();

//                    RegistrationAPI();

                } else {
                    passcheck.setError("Please Checked Box");
                    Toast.makeText(_set_password.this, "Please Checked Box", Toast.LENGTH_SHORT).show();
                }

            } else {
                notmatchedtext.setVisibility(View.VISIBLE);
                notmatchedtext.setText("Password not matched");
            }

        } else if (setpassword.getText().toString().isEmpty()){
            errorshow(setpasswordlayout,setpassword);
        }
        else {
            errorshow(comfirmpasswordlayout,comfirmpassword);
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


    void textwatcher(TextInputEditText text,TextInputLayout layout) {

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

    void RegistrationAPI() {

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String first = preferences.getString("Fname",null);
        String lastname = preferences.getString("Lname",null);
        String mobilenumber = preferences.getString("number",null);
        String dateofbirth = preferences.getString("DOb",null);
        String gender = preferences.getString("g",null);
        String pincode = preferences.getString("pincode",null);
        String email = preferences.getString("email",null);
        String courselevel = preferences.getString("qualification",null);

        String registrationURL = URL + "?first=" + first + "&last="+lastname+"&mobile="+mobilenumber+"&email="+email+"&dob="+dateofbirth+"&gender="+gender+"&pincode="+pincode+"&course_level="+courselevel+"&password="+comfirmpassword;

        String Url = "http://192.168.1.69/API/registration.php?first=first&last=lastname&mobile=mobilenumber&email=email&dob=dateofbirth&gender=gender&pincode=pincode&course_level=courselevel&password=comfirmpassword";

        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    int status = obj.getInt("status");

                    String message = obj.getString("Message");
                    int userid = obj.getInt("user_id");

                    if (status == 1) {

                        Toast.makeText(_set_password.this, ""+message, Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences("registrationDetail",MODE_PRIVATE).edit();
                        editor.putInt("userid",userid);
                        editor.commit();


                        startActivity(new Intent(_set_password.this, greeting_Activity.class));
                        overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                        finish();


                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... param) {


                try {
                    java.net.URL url = new URL(param[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }
        }
        registration obj = new registration();
        obj.execute(Url);
    }

}

